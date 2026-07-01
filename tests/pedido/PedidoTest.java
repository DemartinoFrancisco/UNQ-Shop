package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Deposito;
import catalogo.Item;
import envio.EstrategiaDeEnvio;
import pago.Cliente;



public class PedidoTest {

	
	
    private Pedido pedido;
    private Cliente clienteMock;
    private EstrategiaDeEnvio envioMock;
    private Deposito depositoMock;
    private Item itemMock1;
    private Item itemMock2;

    
    
    @BeforeEach
    public void setUp() {
        clienteMock = mock(Cliente.class);
        envioMock = mock(EstrategiaDeEnvio.class);
        depositoMock = mock(Deposito.class);
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
        
        pedido = new Pedido(clienteMock, envioMock, depositoMock);
    }



    @Test
    public void test001agregarItemAumentaLaListaDeItems() {
        pedido.agregarItem(itemMock1);
        
        assertEquals(1, pedido.getItems().size());
        assertTrue(pedido.getItems().contains(itemMock1));
    }


    @Test
    public void test002quitarItemRemueveElItemDeLaLista() {
        pedido.agregarItem(itemMock1);
        pedido.quitarItem(itemMock1);
        
        assertEquals(0, pedido.getItems().size());
    }


    @Test
    public void test003calcularTotalProductosSumaLosPreciosFinales() {
        when(itemMock1.getPrecioFinal()).thenReturn(150.0);
        when(itemMock2.getPrecioFinal()).thenReturn(50.0);
        
        pedido.agregarItem(itemMock1);
        pedido.agregarItem(itemMock2);
        
        assertEquals(200.0, pedido.calcularTotalProductos());
    }


    @Test
    public void test004calcularCostoEnvioDelegaEnEstrategiaDeEnvio() {
        String direccionStringDummy = "Calle Falsa 123";
        
        when(clienteMock.getDireccion()).thenReturn(direccionStringDummy);
        when(envioMock.calcularCostoDeEnvio(pedido, direccionStringDummy)).thenReturn(500.0);
        
        double costoEnvio = pedido.calcularCostoEnvio();
        
        assertEquals(500.0, costoEnvio);
        verify(envioMock).calcularCostoDeEnvio(pedido, direccionStringDummy);
    }


    @Test
    public void test005pedidoIniciaEnEstadoBorrador() {
        assertTrue(pedido.getEstado() instanceof Borrador);
    }
    
    
    
}