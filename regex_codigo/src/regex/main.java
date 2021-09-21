package regex;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.regex.Matcher;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner ;

public class main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String linea = "" ; //Donde se va a almacenar los disparos realizados.
		File f = new File( "/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/Tinvariantes" ); //Direccion anterior prueba: /home/lucas/concurrentes_2019/T_invariantes/hola
																														//El del ejemplo es: 0,1,2,0,0,5,5,4,8,1,1,9,15,2,4,4,5,0,4,4,5,8,9,1,2,3,4,0,1,2,2,9,
																														//El de la rdp: /home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/Tinvariantes																												
		BufferedReader entrada = null;
		try {
			//Apertura del fichero y creacion de BufferedReader
			entrada = new BufferedReader( new FileReader( f ) );
			// Lectura del archivo
			while(entrada.ready()){
				linea = entrada.readLine(); //guardamos las tranciciones en un String "linea".
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally // En el finally cerramos el fichero.
		{
			try{
				if( null != entrada ){   
					entrada.close();     
				}                 
			}catch(Exception e2){e2.printStackTrace();}
		}
		
		System.out.println("La cadena leida del txt es: ");
		System.out.println(linea);

		String reemplazo = "" ; //Valor que vamos a reemplazar cuando haya coincidencia
		
		List<Pattern> patrones = new ArrayList<>();
		List<Matcher> matchers = new ArrayList<>();
		
		//Pattern patron1 = Pattern.compile(regex1) ; //Patron del camino 1 (regex1)
		//patrones.add(patron1) ;
		//Pattern patron2 = Pattern.compile(regex2) ; //Patron del camino 2 (regex2)
		//patrones.add(patron2) ;
		
		//---------CARGAMOS EL ARREGLO DE PATRONES CON LAS REGEXS DEL TXT----------
		File f1 = new File("/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/regexs"); ///Direccion anterior prueba:  /home/lucas/concurrentes_2019/T_invariantes/regex
																												//Regex de la prueba es : (0,)(?:[0-9]+,)*(1,)(?:[0-9]+,)*(2,)(?:[0-9]+,)*
																												//						  (0,)(?:[0-9]+,)*(5,)(?:[0-9]+,)*(4,)(?:[0-9]+,)*(9,)(?:[0-9]+,)*
																												//El de la rdp: /home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/regexs
																												//	(,0,)(,13,)(,5,)(?:,[0-9]+,)*?(,3,)(?:,[0-9]+,)*?(,4,)
																												//	(,0,)(,1,)(,11,)(?:,[0-9]+,)*?(,9,)(?:,[0-9]+,)*?(,10,)
																												//	(,0,)(,1,)(,7,)(?:,[0-9]+,)*?(,8,)(?:,[0-9]+,)*?(,9,)(?:,[0-9]+,)*?(,10,)(?:,[0-9]+,)*?(,12,)
																												//	(,0,)(,13,)(,14,)(?:,[0-9]+,)*?(,2,)(?:,[0-9]+,)*?(,3,)(?:,[0-9]+,)*?(,4,)(?:,[0-9]+,)*?(,6,)

		BufferedReader entrada1 = null;
		String lectura = "" ;
		try {
			//Apertura del fichero y creacion de BufferedReader
			entrada1 = new BufferedReader( new FileReader( f1 ) );
			// Lectura del archivo
			while(entrada1.ready()){
				lectura = entrada1.readLine();
				patrones.add( Pattern.compile(lectura) ) ; //Cargamos los regex que se encuentran en el txt
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally // En finally cerramos el fichero.
		{
			try{
				if( null != entrada1 ){
					entrada1.close();     
				}                 
			}catch(Exception e2){e2.printStackTrace();}
		}
		
		System.out.println("Se cargaron los patrones.") ;
		
		//Matcher emparejador1 = patron1.matcher(linea) ; //Matcher1 del patron1 (regex1)
		//Matcher emparejador2 = patron2.matcher(linea) ; //Matcher2 del patron2 (regex2)
		
		for(Pattern patron : patrones) {
			matchers.add(  patron.matcher(linea)  ) ; //Creamos los matcher con su String al cual tienen que matchear
		}
		System.out.println("Se cargaron los matchers");
		
		StringBuffer sb = new StringBuffer(linea);//NUEVO
		
		int bandera = 0 ;
		
		while(!(sb.length() == 0 ) && !(bandera > patrones.size())) {  //DENTRO DEL WHILE Y ANDA ES ESTO   !(linea.length() == 0 ) && !(bandera == 4)
			for(Matcher m : matchers) {
				int contador = m.groupCount() ;
				
				while(m.find()) {
					bandera = 0 ;
					int aux = 0 ;
						System.out.println("Encontro el camino");
						for(int i =1; i<contador+1;i++) {
					    	  String grupo = m.group(i);
					    	  System.out.println("Muestro el grupo " + m.group(i));
							  sb.replace(m.start(i) - aux, m.end(i) - aux, reemplazo ) ;
							  aux = aux + grupo.length() ;
					          //System.out.println("LA LINEA AHORA ES: " + linea) ;
					          //System.out.println("sb es ahora: " + sb.toString());
					      }
						//m.reset(sb.toString()) ;
						resetear_matchers(matchers, sb) ;
				}
				bandera++ ;
			}
		}
		System.out.println("Modificado " + sb.toString()) ; 
	}

	private static void resetear_matchers(List<Matcher> matchers, StringBuffer sb) {
		// TODO Auto-generated method stub
		for(Matcher m : matchers) {
			m.reset(sb.toString()) ;
			
		}
		
	}

	private static void replaceAll(StringBuffer sb, String grupo, String reemplazo) {
		// TODO Auto-generated method stub
		int index = sb.indexOf(grupo) ;
		sb.replace(index, index+grupo.length(), reemplazo) ;
	}
}