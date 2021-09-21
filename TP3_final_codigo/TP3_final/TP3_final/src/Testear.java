import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Testear {

	private static int i = 0;
	
	public static String testearMarcado(MatrizMarcado marcado, MatrizPinvariantes pinvariantes, MatrizPresultados pinResultados) {

		i++;
		TestPInvariantes.prepare(marcado, pinvariantes, pinResultados);
		Result result = new JUnitCore().run(TestPInvariantes.class); //Ejecuta el test de TestPInvariantes y el resultado lo guarda en result
		
		String resultado = "Ejecución nº: " + i + ". Test ran: " + result.getRunCount() + ", Failed: " + result.getFailureCount() + ".\n";
		
	    if (!result.getFailures().isEmpty()) { resultado = resultado.concat(result.getFailures().toString() + "\n"); }
	    
	    System.out.printf(resultado);
		return resultado;
	}
	
	
}
