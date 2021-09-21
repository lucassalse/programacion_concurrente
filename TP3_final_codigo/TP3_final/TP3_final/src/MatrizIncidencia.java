
public class MatrizIncidencia {

	/* private int[][] matriz = { {-1,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
							   {1,-1,0,0,0,0,0,0,0,0,0,0,0,-1,0},
							   {0,0,-1,0,0,-1,0,0,0,0,0,0,0,1,0},
							   {0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1},
							   {0,0,0,-1,1,0,0,0,0,0,0,0,0,0,0},
							   {0,0,0,1,-1,0,0,0,0,0,0,0,0,0,0},
							   {0,0,1,0,0,0,-1,0,0,0,0,0,0,0,0},
							   {0,0,-1,0,0,0,0,0,0,0,0,0,0,0,1},
							   {0,1,0,0,0,0,0,0,0,-1,0,0,0,0,0},
							   {0,0,0,0,0,0,0,0,0,1,-1,0,0,0,0},
							   {0,0,0,0,0,0,0,0,0,-1,1,0,0,0,0},
							   {0,0,0,0,0,0,0,0,1,0,0,0,-1,0,0},
							   {0,1,0,0,0,0,0,0,-1,0,0,-1,0,0,0},
							   {0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0},
							   {0,0,0,0,0,0,0,-1,0,0,0,0,1,0,0},
							   {0,0,0,-1,0,0,0,0,0,0,0,0,0,1,0}    } ;  */ 	
	/*									
	public MatrizIncidencia(int fila, int columna) {
		matriz = new int[fila][columna];
	}

	
	public MatrizIncidencia(int[][] matriz) {
		this.matriz = matriz;
	}*/
	
		private Lectura lectura;
		private int [][] matriz;
	
	
	public MatrizIncidencia(){
		lectura=new Lectura();
		matriz=lectura.leerMatriz(16,15,"/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/matrices/MatrizIncidencia");
	}
	
	public int[][] getMatriz() {
		return matriz;
	}
	
	public int getValor(int fila, int columna) {
		return matriz[fila][columna];
	}
	/*
	public void setValor(int fila, int columna, int valor) {
		this.matriz[fila][columna] = valor;
	}*/
	public int getFilas() {
		return matriz.length;
	}
	public int getColumnas() {
		return matriz[0].length;
	}
	/*
	public boolean esCero() {
		int aux = 0;

		for (int i = 0; i < this.getFilas(); i++)
			for (int j = 0; j < this.getColumnas(); j++)
				aux = this.getValor(i, j) + aux;

		return aux == 0;
	}
	
	public MatrizIncidencia and(Matriz B) {
		MatrizIncidencia A = this;
		MatrizIncidencia Result = new MatrizIncidencia(B.getFilas(), B.getColumnas());

		for (int i = 0; i < A.getFilas(); i++) {
			for (int j = 0; j < A.getColumnas(); j++) {
				if (A.getValor(i, j) == 1 && B.getValor(i, j) == 1) {
					Result.setValor(i, j, 1);
				} 
				else {
					Result.setValor(i, j, 0);
				}
			}
		}
		return Result;
	}
	
	public String toString() {
		String texto = "";
		for (int i = 0; i < this.getFilas(); i++) {
			for (int j = 0; j < this.getColumnas(); j++) {
				texto += this.matriz[i][j] + " ";
			}
			texto += "\n";
		}
		return texto;
	}*/
	
}