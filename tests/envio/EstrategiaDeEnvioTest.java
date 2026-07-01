package envio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;



public class EstrategiaDeEnvioTest {

	
	
    private EstrategiaDeEnvio estrategiaMock;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        estrategiaMock = new EstrategiaDeEnvio() {
            @Override
            public double calcularCostoDeEnvio(Pedido pedido, String direccion) {
                return 100.0;
            }

            @Override
            public String estimarDiasDeEntrega(Pedido pedido) {
                return "Mockeado";
            }
        };
    }



    @Test
    public void test001EstrategiaDeEnvioDefineCalcularCosto() {
        double costo = estrategiaMock.calcularCostoDeEnvio(pedidoMock, "Prueba");
        
        assertEquals(100.0, costo);
    }


    @Test
    public void test002EstrategiaDeEnvioDefineEstimarDias() {
        String dias = estrategiaMock.estimarDiasDeEntrega(pedidoMock);
        
        assertEquals("Mockeado", dias);
    }

    
    
}