import java.util.concurrent.Semaphore;

/**
 * Clase cuyo fin es generar las colas mediante
 * un semaphore para cada una de las transiciones.
 * Cuando estas transiciones no pueden disparase se encolaran,
 * en su correspondiente lugar.
 * @author lucas salse, Cristian Velazquez
 *
 */
public class Colas {

	private int transiciones; 
	private Semaphore[] colas;
	
	/**
	 * Constructor de la clase cola.
	 * Inicializa un arreglo de semaforo, en cero y del modo ("justo").
	 * @param transiciones - Transiciones  totales.
	 */
	public Colas(int transiciones) {
		this.transiciones = transiciones ;
		colas = new Semaphore[transiciones] ;
		for (int i = 0 ; i < transiciones ; i++) {
			colas[i] = new Semaphore(0,true);
		}
	}
	
	/**
	 * Metodo para encolar una transicion.
	 * @param t - Transicion a encolar.
	 */
	public void adquirirCola(int t) {
		try {
			colas[t].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Liberar un hilo, desencolando dicha transicion.
	 * @param t - Transicion a desencolar.
	 */
	public void liberarHilo(int t) {
		colas[t].release();
	}
	
	/**
	 * Metodo que devuelve una matriz de 1xCant_transiciones
	 * en donde hay un 1 si es que dicha transicion tiene elementos en la cola,
	 * de lo contrario hay un 0 en dicha posicion.
	 *
	 * @return Matriz - Matriz con un 1 si hay encolados o un 0 si no lo hay.
	 */
	public Matriz quienesEstan() {
		Matriz enCola = new Matriz(1, transiciones);
		
		for (int i = 0 ; i < transiciones ; i++) {
			if (colas[i].hasQueuedThreads()) { //Hay hilos en esa cola.
				enCola.setValor(0, i, 1) ; 
			} 
			else { 
				enCola.setValor(0, i, 0) ; 
			}
		}
		return enCola;
	}
	/**
	 * Metodo utilizado para desencolar
	 * el primer hilo que posee encolado
	 * 
	 * @return - Transicion del hilo a desencolar
	 */
	public int desencolar_hilo() {
		
		Matriz enCola = quienesEstan() ;
		
			for(int i = 0 ; i < enCola.getColumnas() ; i++) {
				if(enCola.getValor(0, i) == 1) {
					return i ;
				}
			}
		return 0 ;	
	}
	/**
	 * Metodo que devuelve si un booleano
	 * para determinar si exsisten hilos encolados
	 * 
	 * @return - True : Hay hilos encolados
	 * 		   - False : No ha hilos encolados
	 */
	public boolean hay_encolados() {
		Matriz enCola = quienesEstan() ;
			for(int i = 0 ; i < enCola.getColumnas() ; i++) {
				if(enCola.getValor(0, i) == 1){
					return true ;
				}
			}
		return false ;
	}
}