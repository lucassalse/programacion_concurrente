
public class MatrizTinvariantes {

/*	private int[][] matriz = { {1,0,0,1,1,1,0,0,0,0,0,0,0,1,0},
			                   {1,1,0,0,0,0,0,0,0,1,1,1,0,0,0},
	                           {1,1,0,0,0,0,0,1,1,1,1,0,1,0,0},
                               {1,0,1,1,1,0,1,0,0,0,0,0,0,1,1}
							      } ; */   	
		
	private Lectura lectura;
	private int [][] matriz;
	
	/*public MatrizTinvariantes(int fila, int columna) {
		matriz = new int[fila][columna];
	}

	
	public MatrizTinvariantes(int[][] matriz) {
		this.matriz = matriz;
	}*/
	
	public MatrizTinvariantes(){
		lectura=new Lectura();
		matriz=lectura.leerMatriz(4,15, "/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/matrices/MatrizTinvariantes");
		
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
	
	public Matriz and(Matriz B) {
		MatrizTinvariantes A = this;
		Matriz Result = new Matriz(B.getFilas(), B.getColumnas());

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
	}*/
	
}