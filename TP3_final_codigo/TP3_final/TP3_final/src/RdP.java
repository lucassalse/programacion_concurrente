import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

//import java.util.HashMap;

/**
 * Clase Red de Petri.
 * Contiene la Rdp con sus matrices correspondientes y sus tiempos en caso de ser
 * transiciones con tiempo.
 * @author lucas salse
 *
 */
public class RdP {
	private int transiciones;
	private int plazas ;
	private MatrizMarcado marcado ;
	private MatrizIncidencia matrizI ;
    private MatrizBackwardsI matrizIneg; //NUEVO
       // private long tiempoInicial=0; // NUEVO
       // private long tiempoFinal=0;	
	private long ventana ; 
	private int [] alfa = {10,0,0,0,20,0,0,0,0,0,20,0,0,0,0} ;//100 1000 1000
	private int[] beta =  {0,0,0,0,2000,0,0,0,0,0,2000,0,0,0,0} ; // 2000 2000
	private long[] timestamp = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ;
	
	private MatrizPinvariantes pInvariantes ;
	private MatrizPresultados pResultados ;
	
	private Imprimir imprimir ;
	
	//private Semaphore semaforoTest ;
	
	private final ReentrantLock lock=new ReentrantLock(true);
	private volatile long IdHilo=0;
	
	
	/**
	 * Constructor de la clase.
	 */
	public RdP() {
		
		marcado = new MatrizMarcado() ;
		matrizI = new MatrizIncidencia() ;
		plazas = matrizI.getFilas() ; 
		transiciones = matrizI.getColumnas() ;
		pInvariantes = new MatrizPinvariantes() ;
		pResultados = new MatrizPresultados() ;
		
		matrizIneg = new MatrizBackwardsI() ;
		
		imprimir = new Imprimir() ;
		
		//semaforoTest = new Semaphore(1,true) ;
		
		//politicaEntrada = new Politicas(devuelvo()) ;
	}
	
	/**
	 * @return - retorna la cantidad de transiciones
	 */
	public int getTrans() {
		return matrizI.getColumnas() ;
	}
	/**
	 * Meotdo que se encraga de disparar una transicion.
	 * @param t - Transicion a disparar
	 * @return - True: si se pudo disprar; false: si no se pudo disprar.
	 */
	public boolean disparar(int t) {
		lock.lock();
		//long time;
		//if(tiempoInicial==0) { //NUEVOO
		//tiempoInicial = System.currentTimeMillis();}
		
		//Corroboramos que dicha transicion este sencibilizada.
		if(estaSencibilizada(t)) {
			
			ventana = getVentanaTiempo(t) ; //Obtenemos la ventana de tiempo.
			chequearFlag();
			
			if(ventana == 0) { //Transicion a disparar atomica o esta dentro de la ventana de tiempo.
			nuevoEstado(t) ;
			calculoTimeStamp();
			System.out.println("Soy el hilo "+Thread.currentThread().getName()+" que DISPARO la transicion: "+ t) ;
			testearMarcado();
			
			imprimir.escribir_Txt_tinvariantes(t);
			imprimir.imprimiendo(t);//NUEVOOOO
			lock.unlock();
			return true ;
			}
			else {
				lock.unlock();
				return false;
			}
		}
		else {
			ventana = 0 ; //Deberia ir porque la ventana queda guardada con un valor anterior entonces podria poner a dormir en el monitor a un hilo anda que ver que no tiene tiempo.
			//Se soluciono con el chequearFlag.
			lock.unlock();
			return false ;
			
		}
	}
	/**
	 * Determina si la transicion pasada como parametro esta
	 * sencibilizada.
	 * @param t - Transicion a analizar
	 * @return - True: si esta sencibilizada; False: si no esta sencibilizada.
	 */
	public boolean estaSencibilizada(int t) {  
		Matriz s = getVectorSencibilizadas() ;
		
		if (s.getValor(0, t) == 1) { 
			return true ; 
		}
		else {
			return false ;
		}	
	}
	
	/**
	 * Devuelve el vector sencibilizado.
	 * @return - Matriz sencibilizada.
	 */
	public Matriz getVectorSencibilizadas() {
		int f;
		Matriz sencib = new Matriz(1, transiciones);
		
		for (int i = 0 ; i < transiciones ; i++) {
			f = 0;
			for (int j = 0 ; j < plazas ; j++) {
				if (marcado.getValor(0, j) + matrizIneg.getValor(j, i) >= 0){
					sencib.setValor(0, i, 1) ;
				}
				else if (marcado.getValor(0, j) + matrizIneg.getValor(j, i) < 0){
					//sencib.setValor(0, i, 0) ;
					f++ ;
				}
			}
			if (f > 0) { 
				sencib.setValor(0, i, 0) ; 
			}
		}
		
			//INHIBIDORES
			if( (devuelvo_marcado().getValor(0, 8) > 0) || (devuelvo_marcado().getValor(0, 9) > 0) ) { //(p2 || p3)
				sencib.setValor(0, 12, 0);
			}
			
			//INHIBIDORES
			if	((devuelvo_marcado().getValor(0, 5) > 0) || (devuelvo_marcado().getValor(0, 15) > 0) ){// p13 || p9
				sencib.setValor(0, 6, 0);
			}		
		return sencib;
	}
	
	/**
	 * Cambiamos el estado del marcado por el dispara de la transicion
	 * @param t - Transicion disparada
	 */
	public void nuevoEstado(int t) {
		for (int i = 0 ; i < plazas ; i++) {
			int valorMarcado = marcado.getValor(0, i) + matrizI.getValor(i,	t) ;
			marcado.setValor(0, i, valorMarcado) ;
		}
	}
	
	public MatrizMarcado devuelvo_marcado() {
		return marcado ;
	}
	public MatrizIncidencia devuevo2() {
		
		return matrizI ;
	}
	public void imprimirMarcado() {
		for (int x=0; x < marcado.getFilas(); x++) {
			  System.out.print("|");
			  for (int y=0; y < marcado.getColumnas(); y++) {
			    System.out.print (marcado.getValor(x, y));
			    if (y!=marcado.getColumnas()-1) System.out.print("\t");
			  }
			  System.out.println("|");
			}
		
	}
	
	
/**
 * Devuelve la vevntana de tiempo.
 * @param t - Transicion
 * @return - :
 * 				0 :   si no posee tiempo la transicion o esta dentro de la ventana.
 * 				long: transicion con tiempo y esta antes de la ventana (lo que va a dormir el hilo para alanzar la ventana de tiempo).
 * 				-1 :  Transicion con tiempo y despues de la ventana del tiempo.
 */
public long getVentanaTiempo(int t) {
		
		long tiempo = System.currentTimeMillis(); //Tiempo para determinar si una t esta o no en tiempo. (se debera restar con el timestamp).
		//Es una transicion sin tiempo
		if (!tConTiempo(t)) { return 0; }
		//Es una transicion con tiempo y esta dentro de la ventana
		else if (enTiempo(t,tiempo) == 1) { return 0; }
		//Es una transicion con tiempo y esta antes de la ventana
		else if (enTiempo(t,tiempo) == 0) { return aDormir(t,tiempo); }
		//Es una transicion sin tiempo o con tiempo despues de la ventana
		return -1;
	}

/**
 * Indica si es una transicion con tiempo o no lo es.
 * @param t - La transicion a analizar.
 * @return - True: si es una t con tiempo ; false: si es una transicion sin tiempo
 */
public boolean tConTiempo(int t) {
	if (alfa[t] != 0 || beta[t] != 0) { return true; }
	else { return false; }
}
	
/**
 * Metodo que controla si la transicion esta o no en tiempo
 * @param trans- transicion a verificar
 * @param tiempo - tiempo transcurrido
 * @return - 0 : Fuera de tiempo (falta)
 * 			 1 : En tiempo.
 * 			 20: Fuera de la ventana
 */
public int enTiempo(int trans, long tiempo) {
	int a = alfa[trans];
	int b = beta[trans];
	long t = tiempo - timestamp[trans];
	
	// DENTRO DEL IF beta.esCero()
	if (b == 0) { //Controlamos si no tiene limite
		if (a > t) {
			return 0; //Fuera de tiempo (falta)
		}
		else {
			return 1; //En tiempo.
		}	
	}
	else {//Tiene limite (b)
		if (a > t) return 0; //Fuera de tiempo (falta)
		else if (a < t && b > t) return 1; //Dentro de la vevntana
		else return 20 ; //Fuera de la ventana
	}
}

/**
 * Metodo que devuelve el tiempo que debe dormir el hilo para poder dispararse.
 * @param trans - Transicion
 * @param tiempo - tiempo total
 * @return - Tiempo que debe dormir para alcanzar la ventana
 */
public long aDormir(int trans, long tiempo) {
	int a = alfa[trans];
	return (a - (tiempo - timestamp[trans]));
}
	
/**
 * Obtenemos los tiempos de las transiciones sencibilizadas,
 * de lo contrario se limpian.
 */
public void calculoTimeStamp() {
	long tiempo = System.currentTimeMillis();
	
	for (int i = 0 ; i < timestamp.length ; i++) {
		if (estaSencibilizada (i)) { timestamp[i] = tiempo; }
		else { timestamp[i] = 0; }
	}	
}

/**
 * Metodo que devuelve si hay alguna transicion
 * sencibilizada
 * 
 * @return - True : Al menos una transicion sencibilizada
 * 		   - False : Ninguna transicion sencibilizada
 */
public boolean hay_sencibilizados() {
	Matriz s = getVectorSencibilizadas() ;
	
	for (int i=1 ; i < s.getColumnas() ; i++) {
		if(s.getValor(0, i)  == 1) {
			return true ;
		}
	}	
	return false ;
}


/**
 * @return long - devuelve la ventana.
 */
public long getVentana() {
	return ventana;
	
}

/**
 * Chequea y modifica la prioridad del hilo, en caso de que se deba disparar.
 */
/*
public void chequearFlag () {
	if (Thread.currentThread().getPriority() == Thread.MAX_PRIORITY) {
		ventana = 0; 
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
	}
}*/
public void chequearFlag (){
	if (Thread.currentThread().getId()==IdHilo) {
		ventana = 0 ; 
	    IdHilo = 0 ;	
	}
}


public boolean preguntarPrioridad(){
	if(IdHilo == 0) {
		return true;
	}else return false;
}

/**
 * Metodo que setea la id del hilo
 * correspondiente a disparar
 * 
 * @param IdHilo - id del hilo
 */
public void recibeId(long IdHilo){
	this.IdHilo = IdHilo;
}

/**
 * Metodo que testea el marcado (P-invariantes)
 */
public void testearMarcado (){
	//semaforoTest.acquire();
	System.out.println(Testear.testearMarcado(marcado, pInvariantes, pResultados)) ;
	
}

}


