
/**
 * Clase que implementa la interfaz Runnable, haciendo referencia
 * a los procesos que llegan a los CPU para ser procesados.
 * @author lucas salse, Cristian Velazauez
 *
 */
public class Arrival  implements Runnable {
	
	private GestorDeMonitor monitor ;
	private int[] secuencia ;
	private Imprimir imprimir ;

	/**
	 * Constructor de la clase Arrival (procesos que llegan a los CPU)
	 * @param monitor - Monitor encargado de controlar y administrar los hilos.
	 * @param secuencia - Secuencia a seguir por dicho hilo.
	 * @param imprimir - Objeto necesario para imprimir lo realizado.
	 */
	public Arrival(GestorDeMonitor monitor, int[] secuencia, Imprimir imprimir){
		this.monitor = monitor ;
		this.secuencia = secuencia ;
		this.imprimir = imprimir ;
	}
	
	@Override
	public void run() {

		while(monitor.get_arribos() < 1000) {
			
			for(int i = 0 ; i<secuencia.length ; i++) {
				//System.out.println("Voy a disparar(arrival): "+ i );
				monitor.dispararTransicion(secuencia[i]) ;
				//imprimir.imprimiendo(secuencia[i]) ;
			}
		}
		System.out.println("SOY EL HILO " + Thread.currentThread().getName() + " TERMINE MI WHILE: ");
	}

}
