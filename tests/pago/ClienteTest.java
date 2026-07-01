package pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ClienteTest {

	
	
    private Cliente cliente;
    private MedioDePago medioDePagoMock;

    
    
    @BeforeEach
    public void setUp() {
        medioDePagoMock = mock(MedioDePago.class);
        cliente = new Cliente("Francisco", "Barrio Parque Bernal 111", medioDePagoMock);
    }



    @Test
    public void test001ConstructorYGettersDevuelvenLosValoresCorrectos() {
        assertEquals("Francisco", cliente.getNombre());
        assertEquals("Barrio Parque Bernal 111", cliente.getDireccion());
        assertEquals(medioDePagoMock, cliente.getMedioDePago());
    }


    @Test
    public void test002AgregarNotaDeCreditoSeEjecutaCorrectamente() {
        // cómo en ningún momento pidieron que use las notas de credito, no les hice un getter a probar, por lo que solo testeo que se agregue una nota de credito correctamente
        cliente.agregarNotaDeCredito(1500.0);
    }
    
    
    
}