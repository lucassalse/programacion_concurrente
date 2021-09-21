
/**
 * Clase la cual replica el funcionamiento del 
 * "monitor", la misma nos permite el correspondiente uso
 * de la concurrencia de hilos.
 * @author lucas salse, Cristian Velazquez
 *
 */
public class GestorDeMonitor {
	
	private Mutex mutex ;
	private RdP rdp ;
	private Colas colas ;
	private Politicas politicas ;
	private Matriz m ;
	private int arribos = 0 ;
	private boolean seguir = true ;
	
	private boolean flagEncolados ;
	private int cantDisparos = 1000 ;
	
	
	/**
	 * Constructor de GestorDeMonitor, inicializa
	 * los campos necesarios.
	 * 
	 */
	public GestorDeMonitor() {
		mutex = new Mutex() ;
		rdp = new RdP() ;
		colas = new Colas(rdp.getTrans()) ;
		politicas = new Politicas (rdp) ;
		m = new Matriz(1, rdp.getTrans()) ;
		
		flagEncolados = true ;
	}

	/**
	 * Meotdo que disparara si es posible una transicion.
	 * En caso contario se encargará tanto de encolarla o desencolar
	 * alguna que ya puede ser disparada.
	 * 
	 * @param t - Transicion a disparar
	 */
	public void dispararTransicion(int t){
		//////System.out.println("Soy el hilo: "+Thread.currentThread().getName()+" que quiere entrar al monitor a disprar "+t+" ") ;
		mutex.adquirirMutex();
		
		t = politicas.bufferEntrada(t) ; //Determina que camino debe tomar al inicio (a que CPU)
		//////System.out.println("Soy el hilo "+Thread.currentThread().getName()+" que entre al monitor a disparar "+t+" ") ;
		//System.out.println() ;
		//System.out.println() ;System.out.println() ; System.out.println() ;
		while (!rdp.disparar(t) && flagEncolados) {	
			
			mutex.liberarMutex();
			
			//En caso de ser una transicion con tiempo y que cumpla con la siguiente condicion
			//dicho hilo se irá a dormir el tiempo necesario que corresponde.
			if (rdp.getVentana() > 0 && rdp.getVectorSencibilizadas().getValor(0, t) == 1) {
				try { 
					///////System.out.println("Soy el hilo: "+Thread.currentThread().getName()+ " de transicion "+ t +" me duermo: "+ rdp.getVentana() );
					Thread.sleep(rdp.getVentana());
					//////System.out.println("Soy el hilo: "+Thread.currentThread().getName()+ " de transicion "+ t +" me desperte " );
					mutex.adquirirMutex();
					//Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					if(rdp.preguntarPrioridad()==true){
						rdp.recibeId(Thread.currentThread().getId());	
					}
					
				} catch (InterruptedException e) { e.printStackTrace(); } 
			}
			//Si no es una transicion con tiempo, directamente se encola.
			else{ 
				///////System.out.println("Soy el hilo: "+Thread.currentThread().getName()+ " me voy a encolar Porque no pude disparar la transicion: "+ t );
				colas.adquirirCola(t); 
			}
			
			//colas.adquirirCola(t);
		}
		if(t == 0) {
			arribos++ ;
		}
		
		//System.out.println("Soy el hilo "+Thread.currentThread().getName()+" Pude disparar la transicion: "+ t) ;
		//System.out.println("El nuevo marcado es: ");
		//System.out.println() ;
		///////rdp.imprimirMarcado() ;
		Matriz vs = rdp.getVectorSencibilizadas() ; //Obtenemos una matriz con las transiciones sencibilizadas.
		Matriz vc = colas.quienesEstan() ; // Obtenemos las transiciones que estan encoladas.
		
		m = vs.and(vc);
		if (!m.esCero()) {
			//////System.out.println("voy a liberar al hilo encolado ");
			colas.liberarHilo(politicas.cual(m));
		}
		///////System.out.println("Soy el hilo: " Thread.currentThread().getName() + " y me voy del monitor ") ;
		else {
		//	System.out.println("LLEGUE AL ELSE") ;
		//	System.out.println("hay sencibilizados = " + rdp.hay_sencibilizados());
			/*for(int i = 0 ; i < vs.getColumnas() ; i++) {
				System.out.print(" " + vs.getValor(0, i) + ", ") ;
			}*/
		//	System.out.println("hay encolados = " + colas.hay_encolados());
			
			if( (!rdp.hay_sencibilizados() ) && ( colas.hay_encolados() ) && arribos == cantDisparos ) {
				flagEncolados = false ;
				seguir = false ;
				colas.liberarHilo(colas.desencolar_hilo());
			}
			
		}
		
		mutex.liberarMutex();
	}
	
	public int get_arribos() {
		return arribos ;
	}
	public boolean get_seguir() {
		return seguir ;
	}
	
	
}
		
	
	





