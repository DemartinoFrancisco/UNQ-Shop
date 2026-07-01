package envio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import catalogo.Item;
import libreriasEInterfacesExternas.EnvioExpress;
import pedido.Pedido;



public class ExpressTest {

	
	
    private Express express;
    private Pedido pedidoMock;
    private Item itemMock1;
    private Item itemMock2;

    
    
    @BeforeEach
    public void setUp() {
        express = new Express();
        pedidoMock = mock(Pedido.class);
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
        
        List<Item> items = Arrays.asList(itemMock1, itemMock2);
        when(pedidoMock.getItems()).thenReturn(items);
        when(itemMock1.getPrecioFinal()).thenReturn(1000.0);
        when(itemMock2.getPrecioFinal()).thenReturn(2000.0);
    }



    @Test
    public void test001CalcularCostoDeEnvioExpress() {
        try (MockedStatic<EnvioExpress> expressMockeado = mockStatic(EnvioExpress.class)) {
            expressMockeado.when(() -> EnvioExpress.calcularCosto(anyFloat()))
                           .thenReturn(500.0f); // simulamos que la libreria envioExpress nos devuelva 500 cuando le preguntamos por cualquier peso y dirección para poder testear este metodo de envio correctamente
            
            double costo = express.calcularCostoDeEnvio(pedidoMock, "Lebenshon 542, Barrio Parque Bernal");
            
            assertEquals(500.0, costo);
        }
    }


    @Test
    public void test002EstimarDiasDeEntregaExpress() {
        String diasEstimados = express.estimarDiasDeEntrega(pedidoMock);
        
        assertEquals("1 día hábil", diasEstimados);
    }

    
    
}