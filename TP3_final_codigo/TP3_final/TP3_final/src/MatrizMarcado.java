
public class MatrizMarcado {

	//private int[][] matriz = { {1,0,0,1,1,0,0,0,0,0,1,0,0,0,1,0} };
	
	private Lectura lectura;
	private int [][] matriz;
	
	public MatrizMarcado(int fila, int columna) {
		matriz = new int[fila][columna];
	}
	
	public MatrizMarcado(int[][] matriz) {
		this.matriz = matriz;
	}
	
	public MatrizMarcado(){
		lectura=new Lectura();
		this.matriz=lectura.leerMatriz(1,16, "/home/lucas/concurrentes_2019/codigoFuncionando_final/TP3_final/TP3_final/matrices/MatrizMarcado");
		
	}
	
	public int[][] getMatriz() {
		return matriz;
	}
	
	public int getValor(int fila, int columna) {
		return matriz[fila][columna];
	}
	
	public void setValor(int fila, int columna, int valor) {
		this.matriz[fila][columna] = valor;
	}
	public int getFilas() {
		return matriz.length;
	}
	public int getColumnas() {
		return matriz[0].length;
	}
	
	public boolean esCero() {
		int aux = 0;

		for (int i = 0; i < this.getFilas(); i++)
			for (int j = 0; j < this.getColumnas(); j++)
				aux = this.getValor(i, j) + aux;

		return aux == 0;
	}
	/*
	public Matriz and(Matriz B) {
		MatrizMarcado A = this;
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
	}
	
	public Matriz punto(Matriz B) {
		MatrizMarcado A = this;
		if (A.getColumnas() != B.getFilas()) { throw new RuntimeException("Dimensiones no compatibles."); }
		
		Matriz C = new Matriz(A.getFilas(), B.getColumnas());
		for (int i = 0 ; i < C.getFilas() ; i++) {
			for (int j = 0 ; j < C.getColumnas() ; j++) {
				for (int k = 0 ; k < A.getColumnas() ; k++) {
					C.setValor(i, j, C.getValor(i, j) + (A.getValor(i, k) * B.getValor(k, j)));
				}
			}
		}
		return C;
	}*/
	
	public Matriz transpuesta() {
		Matriz A = new Matriz(this.getColumnas(), this.getFilas());
		for (int i = 0 ; i < this.getFilas() ; i++) {
			for (int j = 0 ; j < this.getColumnas() ; j++) {
				A.setValor(j, i, this.getValor(i, j));
			}
		}
		return A;
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
	}
	
}
