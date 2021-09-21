import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que permite llevar a cabo las impresiones
 * tanto por pantalla como en un archivo.
 * @author lucas salse, Cristian Velazquez
 *
 */
public class Imprimir {
	private Semaphore semaforoImpresion ;
	//private static int buffer1 = 0 ; //Cantidad de actividades en el buffer 1.
	//private static int buffer2 = 0 ; //Cantidad de actividades en el buffer 2.
	private static int total = 0 ; //total de arribos de actividades
	private static int actividadesRealizada1 = 0 ; //cantidad de actividades realizada por el CPU 1
	private static int actividadesRealizada2 = 0 ; //cantidad de actividades realizada por el CPU 2
	FileWriter archivo ;
	PrintWriter escritor ;
	
	private boolean flag = false;
	private static long tiempoInicial ;
	private static long tiempoFinal ;
	private static long time ;
	
	private final ReentrantLock lock=new ReentrantLock(true);
	
	/**
	 * Constructor de la clase Imprimir
	 * en donde inicializamos un semaphore.
	 */
	public Imprimir() {
		semaforoImpresion = new Semaphore (1,true) ;		
	}
	
	/**
	 * Metodo que se encarga de escribir un txt
	 * con las transiciones que se dispararon.
	 * @param t - Transiciones que se dispararon.
	 */
	public void escribir_Txt_tinvariantes(int t) {
		lock.lock();
		
		try {
			FileWriter flwriter1 = new FileWriter("/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/Tinvariantes", true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter1);
			bfwriter.write(","+t+",") ;
			bfwriter.close();
			flwriter1.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	/**
	 * Meotodo que imprime informacion por consola
	 * 
	 * @param i - Transicion a imprimir.
	 */
	public void imprimiendo(int i){
		try {
			semaforoImpresion.acquire() ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		switch(i) {
		case 1: //T1
			System.out.println("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T1:   buffer CPU 1  ") ;
			break ;
			
		case 13: //T8
			System.out.println("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T1:   buffer CPU 2  ") ;
			break ;
			
		case 0: //T0
			total++ ; //Total de procesos disparados.
			System.out.println("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T0:   arribo un proceso  ") ;
			//System.out.println("La transicion disparada 2: La cantidad de productos en el buffer1 es: "+productos1);
			
			System.out.println("Arribos totales : "+ total) ;
			//System.out.println("ELEMENTOS BUFFER 2 : "+ productos2) ;
			/*
			try {
				FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter1);
				bfwriter.write("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T0:   arribo un proceso  ") ;
				bfwriter.newLine() ;
				bfwriter.write("Arribos totales : "+ total) ;
				bfwriter.newLine() ;
				
				
				bfwriter.close();
				flwriter1.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//controlarTiempo() ;
			break ;
			
		case 10: //T5
			actividadesRealizada1++ ;
			//buffer1-- ;
			System.out.println("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T5:  proceso realizado CPU1  ") ;
			//System.out.println("La transicion disparada 3: La cantidad de productos en el buffer2 es: "+productos2);
			
			System.out.println("Procesos realizados por el CPU1 : "+ actividadesRealizada1) ;
			//System.out.println("ELEMENTOS BUFFER 2 : "+ productos2) ;
			/*
			try {
				FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter1);
				bfwriter.write("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T5:  proceso realizado CPU1  ") ;
				bfwriter.newLine() ;
				bfwriter.write("Procesos realizados por el CPU1 : "+ actividadesRealizada1) ;
				bfwriter.newLine() ;
				
				
				
				bfwriter.close();
				flwriter1.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//controlarTiempo() ;
			break ;
			
			
		case 4: //T12
			actividadesRealizada2++ ;
			//buffer2-- ;
			System.out.println("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T12:  proceso realizado CPU2  ") ;
			//System.out.println("La transicion disparada 3: La cantidad de productos en el buffer2 es: "+productos2);
			
			System.out.println("Procesos realizados por el CPU2 : "+ actividadesRealizada2) ;
			//System.out.println("ELEMENTOS BUFFER 2 : "+ productos2) ;
			/*
			try {
				FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter1);
				bfwriter.write("Soy el hilo: "+Thread.currentThread().getName() +"  transicion disparada T12:  proceso realizado CPU2  ") ;
				bfwriter.newLine() ;
				bfwriter.write("Procesos realizados por el CPU2 : "+ actividadesRealizada2) ;
				bfwriter.newLine() ;
				
				
				
				bfwriter.close();
				flwriter1.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//controlarTiempo() ;
			break ;
				
		/*	
		case 9: //T4 
			buffer1-- ;
			break ;
			
		case 3: //T11 
			buffer2-- ;
			break ;
		
		*/	
		case 12: //T7
			
			System.out.println("Soy el hilo "+Thread.currentThread().getName() + "transicion disparada T7: se apago la CPU1 ") ;
			/*
			try {
				FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter1);
				bfwriter.write("Soy el hilo "+Thread.currentThread().getName() + "transicion disparada T7: se apago la CPU1 ") ;
				bfwriter.newLine() ;
				
				
				bfwriter.close();
				flwriter1.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			//controlarTiempo() ;
			
			break ;
			
		case 6: //T14
			
			System.out.println("Soy el hilo "+ Thread.currentThread().getName() + "transicion disparada T14: se apago la CPU2 ") ;
			/*
			try {
				FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter1);
				bfwriter.write("Soy el hilo "+ Thread.currentThread().getName() + "transicion disparada T14: se apago la CPU2 ") ;
				bfwriter.newLine() ;
				
				
				bfwriter.close();
				flwriter1.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			//controlarTiempo() ;
			break ;
		}
		
		
		
	//	System.out.println("Han arrivado: " +total+" actividades. ") ;
	//	System.out.println("CANTIDAD  BUFFER 1 : "+ buffer1) ;
	//	System.out.println("CANTIDAD  BUFFER 2 : "+ buffer2) ;
		
		System.out.println("**************************************************************") ;
		/*try {
			FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\Facultad\\2019\\Programacion concurrente\\control\\log.txt", true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter1);
			bfwriter.write("Han arrivado: " +total+" actividades. ") ;
			bfwriter.newLine() ;
		//	bfwriter.write("CANTIDAD  BUFFER 1 : "+ buffer1);
		//	bfwriter.newLine() ;
		//	bfwriter.write("CANTIDAD BUFFER 2 : "+ buffer2) ;
		//	bfwriter.newLine() ;
			bfwriter.write("**************************************************************") ;
			bfwriter.newLine() ;
			bfwriter.close();
			
			
			flwriter1.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		semaforoImpresion.release();
		
		
		
	}
	
	/**
	 * Funcion que setea e imprime el tiempo inicial.
	 * @param tiempoInicial - Tiempo inicial.
	 */
	public void setTiempoInicial(long tiempoInicial) {
		this.tiempoInicial = tiempoInicial ;
		System.out.println("EL TIEMPO INCIAL ES: " + tiempoInicial);
		/*
		try {
			FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter1);
			bfwriter.write("EL TIEMPO INCIAL ES: " + tiempoInicial) ;
			bfwriter.newLine() ;
			
			
			bfwriter.close();
			flwriter1.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	/**
	 * Imprime el tiempo total que se necesito para
	 * procesar los "n"(1000) cantidad de procesos.
	 */
	public void controlarTiempo(){
		if(actividadesRealizada1 + actividadesRealizada2 == 1000 && (flag==false)) {
			tiempoFinal = System.currentTimeMillis();
			time = tiempoFinal- tiempoInicial;
			flag = true ;
			System.out.println("el tiempo final es "+ time) ;
			/*
			try {
				FileWriter flwriter1 = new FileWriter("C:\\Users\\lucas\\Desktop\\TP3 [FINAL ENTREGA]\\codigoFuncionando\\TP3\\TP3\\log.txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter1);
				bfwriter.write("el tiempo final es "+ time) ;
				bfwriter.newLine() ;
				
				
				bfwriter.close();
				flwriter1.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
		
	}
	public int get_actividadesRealizadas1() {
		return actividadesRealizada1 ;
	}
	
	public int get_actividadesRealizadas2() {
		return actividadesRealizada2 ;
	}
	
	

}
