package envio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Deposito;
import catalogo.Item;
import pedido.Pedido;



public class SucursalTest {

	
	
    private Sucursal sucursal;
    private Deposito depositoMock;
    private Pedido pedidoMock;
    private Item itemMock;
    private List<Item> itemsPedido;

    
    
    @BeforeEach
    public void setUp() {
        depositoMock = mock(Deposito.class);
        sucursal = new Sucursal(depositoMock);
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        
        itemsPedido = Arrays.asList(itemMock);
        when(pedidoMock.getItems()).thenReturn(itemsPedido);
    }



    @Test
    public void test001CalcularCostoDeEnvioSucursalEsCero() {
        double costo = sucursal.calcularCostoDeEnvio(pedidoMock, "Sucursal Central");
        
        assertEquals(0.0, costo);
    }


    @Test
    public void test002EstimarDiasDeEntregaConStockInmediato() {
        when(depositoMock.tieneStockDeItems(itemsPedido)).thenReturn(true);
        
        String diasEstimados = sucursal.estimarDiasDeEntrega(pedidoMock);
        
        assertEquals("Inmediato", diasEstimados);
    }


    @Test
    public void test003EstimarDiasDeEntregaSinStockLocal() {
        when(depositoMock.tieneStockDeItems(itemsPedido)).thenReturn(false);
        
        String diasEstimados = sucursal.estimarDiasDeEntrega(pedidoMock);
        
        assertEquals("Hasta 3 días", diasEstimados);
    }

    
    
}