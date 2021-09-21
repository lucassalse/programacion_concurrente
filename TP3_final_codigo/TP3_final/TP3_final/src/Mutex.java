
import java.util.concurrent.Semaphore;

/**
 * Clase mutex, que limita la entrada al monitor
 * mediante un semaphore.
 * @author lucas salse, Cristian velazquez
 *
 */
public class Mutex {
	
private Semaphore mutex;
	
	/**
	 * Cnostrucotr de la clase Mutex
	 * inicializa el semaphore en 1 y fernest(justo)
	 */
	public Mutex () {
		mutex = new Semaphore(1,true) ;
	}
	
	public void adquirirMutex () {
		try {
			mutex.acquire() ;
		} catch (InterruptedException e) {
			e.printStackTrace() ;
		}
	}
	
	public void liberarMutex () {
		mutex.release();
	}		

}
	


