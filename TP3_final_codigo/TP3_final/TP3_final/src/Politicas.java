import java.util.ArrayList;

/**
 * Clase Politicas
 * En donde se determina que desicion se debe tomar, en el momento tanto
 * de disparar alguna transicion debido a varias sencibilizados o cual
 * debemos desencolar.
 * @author lucas salse, Cristian Velazquez
 *
 */
public class Politicas {
	
		Matriz matrizP ;
		MatrizMarcado matrizMarcado ;
		RdP rdp;
	
	/**
	 * Constructor de la clase
	 * @param rdp - red de petri utilizada
	 */
	public Politicas(RdP rdp) {
		this.rdp=rdp;
		matrizP = new Matriz (1, rdp.getTrans()) ;
		matrizMarcado = rdp.devuelvo_marcado() ;
		
	}
	/**
	 * Constructor de la clase.
	 * @param matrizMarcado
	 */
	public Politicas(MatrizMarcado matrizMarcado) {
		this.matrizMarcado = matrizMarcado ;
		
	}
	
	/**
	 * Determina cual transicion de las que se peden desencolar
	 * se desencolara.
	 * @param m - Matriz que contiene transiciones posibles a desencolar.
	 * @return - La transicion a desencolar.
	 */
	public int cual (Matriz m) {
		ArrayList<Integer> vectorNuevo = new ArrayList() ;
		
		int random ;
		int transicion ;
		int cont = 0 ; 
		int posicion = 0 ;
		for(int i = 0 ; i<m.getColumnas(); i++) {
			
			
			if(m.getValor(0, 7) == 1) {
				return 7 ;
			}
			
			if(m.getValor(0, 14) ==  1 ) {
				return 14 ;
			}
			
			if(m.getValor(0, 9) == 1) {
				return 9 ;
				
			}
			
			if(m.getValor(0, 3) == 1) {
				return 3 ;
			}
			
			if(m.getValor(0, 11) == 1) {
				return 11 ;
				
			}
			if(m.getValor(0, 5) == 1) {
				return 5 ;
			}
			
			
			if(m.getValor(0, 12) == 1) {
				return 12 ;
				
			}
			
			if(m.getValor(0, 6) == 1) {
				return 6 ;
				
			}
			
			
			//En caso de no ser alguna de las transiciones anteriores.
			//Las colocamos en un arrayList y luego seleccionamos la transicion mediante un Random
			
			if( m.getValor(0, i) == 1 ) {
		
				posicion = i ;
				vectorNuevo.add(posicion) ;
				cont++ ;
			}	
		}
		
		random = (int) ( Math.random() * (cont-1)) ; //Valores entre 0 y cont-1: si cont es 4 valores de 0 a 3
		transicion = vectorNuevo.get(random) ;
		
		vectorNuevo.clear();
		return transicion ;
		
		
	}
	/**
	 * Metodo que determina que procesador utilizar al momento de que un proceso
	 * ingresa. (Cirterio usado: el proceso va al CPU con menor demanda y si son iguales se realiza un random)
	 * @param t - transicion a disparar.
	 * @return - retorna la transicion a disparar (CPU a usar)
	 */
	public int bufferEntrada(int t) {
		////System.out.println("El valor de p2 es: " + matrizMarcado.getValor(0, 8) + "El valor de p9 es: "+ matrizMarcado.getValor(0, 15)) ;
                int random ;
                int[] buffers = {1,13} ;
		switch (t){
			case 1:
				if(matrizMarcado.getValor(0, 8) > matrizMarcado.getValor(0, 15) ) {// (p2 > p9 )
					return 13 ;
				}
				else {if(matrizMarcado.getValor(0, 8) < matrizMarcado.getValor(0, 15)) {// (p2 < p9 )
					return 1 ;
                                        }
                                      else{random = (int) ( Math.random() * (2)) ; 
                                      return buffers[random]; }
					
				}
			
		}
		return t ;	
	}
}
