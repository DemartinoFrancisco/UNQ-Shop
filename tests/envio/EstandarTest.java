package envio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import libreriasEInterfacesExternas.CorreoArgentina;
import libreriasEInterfacesExternas.Direccion;
import pedido.Pedido;



public class EstandarTest {

	
	
    private Estandar estandar;
    private Pedido pedidoMock;
    private Item itemMock1;
    private Item itemMock2;
    
    

    @BeforeEach
    public void setUp() {
        estandar = new Estandar();
        pedidoMock = mock(Pedido.class);
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
        
        List<Item> items = Arrays.asList(itemMock1, itemMock2);
        when(pedidoMock.getItems()).thenReturn(items);
        when(itemMock1.getPeso()).thenReturn(2.5);
        when(itemMock2.getPeso()).thenReturn(3.5);
    }



    @Test
    public void test001CalcularCostoDeEnvioEstandar() {
        try (MockedStatic<CorreoArgentina> correoMockeado = mockStatic(CorreoArgentina.class)) {
            correoMockeado.when(() -> CorreoArgentina.estimarEnvio(anyFloat(), any(Direccion.class)))
                          .thenReturn(1500.0f); // simulamos que correoArgentina nos devuelva 1500 cuando le preguntamos por cualquier peso y dirección para poder testear este metodo de envio correctamente
            
            double costo = estandar.calcularCostoDeEnvio(pedidoMock, "Calle Falsa 123");
            
            assertEquals(1500.0, costo);
        }
    }


    @Test
    public void test002EstimarDiasDeEntregaEstandar() {
        String diasEstimados = estandar.estimarDiasDeEntrega(pedidoMock);
        
        assertEquals("Entre 5 y 7 días hábiles", diasEstimados);
    }
    
    

}