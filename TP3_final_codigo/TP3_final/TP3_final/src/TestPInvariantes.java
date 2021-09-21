import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class TestPInvariantes {
	
private static MatrizPinvariantes pinvariantes ;
private static MatrizPresultados resultados ; 
private static MatrizMarcado marcado ;
	
	public static void prepare(MatrizMarcado marcado, MatrizPinvariantes pinvariantes, MatrizPresultados resultados) {	//GUARDA LOS VALORES QUE RECIBE
		TestPInvariantes.pinvariantes = pinvariantes;										//EN LOS CAMPOS ESTOS
		TestPInvariantes.resultados = resultados;
		TestPInvariantes.marcado = marcado;
    }
	
	@Before
	public void carga() {
		pinvariantes = pinvariantes.punto(marcado.transpuesta()).transpuesta(); //Antes de ejecutar el test hace esto si o si
	}
	
	@Test
	public void pinvariantes() {
		Assert.assertEquals(pinvariantes.toString(), resultados.toString()); //Corre el test que compara
	}
	

}
