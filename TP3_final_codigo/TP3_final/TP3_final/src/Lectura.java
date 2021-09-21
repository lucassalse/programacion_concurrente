import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase utilizada para poder cargar las matrices a utilizar
 * mediante un archivo txt, colocando la correspondiente ruta (path)
 * @author lucas salse, Cristian Velazquez
 *
 */
public class Lectura{

	private int [][] matriz;


	/**
	 * Constructor de la clase Lectura
	 * 
	 */
	public Lectura() {

	}
	
	
	/**
	 * Metodo que carga las matrices desde un txt
	 * 
	 * @param fila - cantidad de filas
	 * @param columna - cantidad de columnas
	 * @param direccion - path donde se encuentra el txt con la matriz
	 * @return
	 */
	public int[][] leerMatriz(int fila, int columna,String direccion){
		File archivo = null;
		FileReader Fr = null;
		BufferedReader br = null;
		try {
			archivo = new File(direccion);
			Fr = new FileReader(archivo.toString());
			br = new BufferedReader(Fr);
			String linea;
			String delimiter = ",";
			//Matriz estática de 15 x 16
			matriz= new int[fila][columna];
			//Cuenta las líneas y a la vez sería el número de filas
			int numlinea=0;
			//validación si existe línea
			while (((linea = br.readLine()) != null)) {
            //Imprime la línea
            //System.out.println(linea);
            //Guardar datos de linea en Array
            String a[]=linea.split(delimiter);
           //Bucle para poder ingresar todas las columnas del Array "a" que existan. TODAS.
            	for (int l = 0; l < a.length; l++) {
            		//ingresamos los datos de cada columna de "a" a la matriz.
            		//"numlinea" hace de fila, "l" es el numero de la columna.
            		matriz[numlinea][l] = Integer.parseInt(a[l],10);
            		//Prueba de que los datos están llenando la fila de la matriz.
            		//System.out.print(matriz[numlinea][l]+" ");  
            	}
            //Incremento de numero de línea.
            numlinea++;
			}
        //Impresión de la matriz
		/*	
         System.out.println("MATRIZ");
         System.out.println("------");
           for (int filas = 0; filas < matriz.length; filas++) {
                for (int colum = 0; colum < matriz[filas].length; colum++) {
                    //Imprime las columnas de cada fila
                    System.out.print(matriz[filas][colum]+" ");
                }
                //Imprime uns alto de línea para cada fila
                System.out.println();   
            }*/
		} catch (IOException e) {
			System.out.println(e);
			}
		return matriz; 
	}
}