package pedido;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class OperacionInvalidaExceptionTest {

	
	
    private OperacionInvalidaException excepcion;

    
    
    @BeforeEach
    public void setUp() {
        excepcion = new OperacionInvalidaException("Estado inválido para la acción");
    }



    @Test
    public void test001excepcionGuardaElMensajeCorrectamente() {
        assertEquals("Estado inválido para la acción", excepcion.getMessage());
    }
    
    
    
}