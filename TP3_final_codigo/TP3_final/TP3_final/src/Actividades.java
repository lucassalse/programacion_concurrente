
/**
 * Clase Actividades que representa los hilos empleados.
 * Implementa la interfaz Runnable.
 * @author lucas salse, Cristian Velazquez
 *
 */
public class Actividades  implements Runnable {
	
	private GestorDeMonitor monitor ;
	private int[] secuencia ;
	private Imprimir imprimir ;

	/**
	 * Constructor de la clase Actividades.
	 * @param monitor - Monitor encargado de controlar y administrar los hilos.
	 * @param secuencia - Secuencia a seguir por dicho hilo.
	 * @param imprimir - Objeto necesario para imprimir lo realizado.
	 */
	public Actividades(GestorDeMonitor monitor, int[] secuencia, Imprimir imprimir){
		this.monitor = monitor ;
		this.secuencia = secuencia ;
		this.imprimir = imprimir ;
	}
	
	@Override
	public void run() {
	
		while(monitor.get_seguir()) {
			
			for(int i = 0 ; i<secuencia.length ; i++) {
				monitor.dispararTransicion(secuencia[i]) ;
				//if(monitor.get_seguir())
				//	imprimir.imprimiendo(secuencia[i]) ;
			}
		}
		System.out.println("SOY EL HILO " + Thread.currentThread().getName() + " TERMINE MI WHILE: ");
	}

}
