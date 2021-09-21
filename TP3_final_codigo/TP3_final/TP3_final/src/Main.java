import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase main encargada de llevar a cabo las actividades de los
 * correspondientes hilos.
 * @author lucas salse
 *
 */
public class Main {

	private static long tiempoInicial = 0; // NUEVO
	private static long tiempoFinal = 0; // NUEVO
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Thread> hilos = new ArrayList<>();
		
		limpiar_fichero() ; //Limpiamos el fichero al inicio.
		
		GestorDeMonitor monitor= new GestorDeMonitor();
		Imprimir imprimir = new Imprimir() ;
		
		//Arreglo de secuenncias(Transiciones) que debe cumplir cada uno de los hilos utilizados.
		int[] secuenciaT0 = {0,1} ; //{T0,T1;T8}
		int[] secuenciaT2 = {7,8,12} ;    //{T2,T3,T7}
		int[] secuenciaT4 = {9,10} ;   //{T4,T5}
		int[] secuenciaT6 = {11} ;	   //{T6}
		int[] secuenciaT9 = {14,2,6} ;   //{T9,T10,T14}
		int[] secuenciaT13 = {5} ;	   //{T13}
		int[] secuenciaT11 = {3,4} ;   //{T11,T12}
		
		//Creacion de los objetos que van a implemetar la interfaz Runnable.
		Arrival arrival   = new Arrival (monitor, secuenciaT0, imprimir) ;
		Actividades actividad1 = new Actividades (monitor, secuenciaT2, imprimir) ;
		Actividades actividad2 = new Actividades (monitor, secuenciaT4, imprimir) ;
		Actividades actividad3 = new Actividades (monitor, secuenciaT6, imprimir) ;
		Actividades actividad5 = new Actividades (monitor, secuenciaT9, imprimir) ;
		Actividades actividad6 = new Actividades (monitor, secuenciaT13, imprimir) ;
		Actividades actividad7 = new Actividades (monitor, secuenciaT11, imprimir) ;
		
		//Creacion de los hilos utilizados, mediante la utilizacion de la interfaz Runnable
		Thread hiloArrival = new Thread (arrival) ; hilos.add(hiloArrival) ;
		Thread hiloActividad1 = new Thread (actividad1) ; hilos.add(hiloActividad1) ;
		Thread hiloActividad2 = new Thread (actividad2) ; hilos.add(hiloActividad2) ;
		Thread hiloActividad3 = new Thread (actividad3) ; hilos.add(hiloActividad3) ;
		Thread hiloActividad5 = new Thread (actividad5) ; hilos.add(hiloActividad5) ;
		Thread hiloActividad6 = new Thread (actividad6) ; hilos.add(hiloActividad6) ;
		Thread hiloActividad7 = new Thread (actividad7) ; hilos.add(hiloActividad7) ;
		
		/*
		//Comenzamos la ejecucion de los hilos.
		hiloArrival.start();
		hiloActividad1.start();
		hiloActividad2.start();
		hiloActividad3.start();
		hiloActividad5.start();
		hiloActividad6.start();
		hiloActividad7.start(); */
		
		if(tiempoInicial==0) { //NUEVOO
			tiempoInicial = System.currentTimeMillis(); //Iniciamos a contar el tiempo transcurrido.
		}
		imprimir.setTiempoInicial(tiempoInicial);  //Seteamos el tiempo inicial.
		
		//Iniciamos la ejecucion de los hilos
		for(Thread hilo : hilos) {
			hilo.start();
		}
		for(Thread t : hilos) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		tiempoFinal = System.currentTimeMillis() ;
		System.out.println("\nSe generó el archivo con los disparos realizados.");
		System.out.println("El tiempo final es: " + (tiempoFinal - tiempoInicial));
		System.out.println("Procesos realizados por el CPU1: " + imprimir.get_actividadesRealizadas1());
		System.out.println("Procesos realizados por el CPU2: " + imprimir.get_actividadesRealizadas2());
		
		
	}
	
	/**
	 * Meotdo utilizado para limpiar el fichero que contiene
	 * las transiciones disparadas.
	 */
	public static void limpiar_fichero() {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/Tinvariantes"));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}


