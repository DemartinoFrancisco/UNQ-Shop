package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class EnPreparacionTest {

	
	
    private EnPreparacion enPreparacion;
    private Pedido pedidoMock;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        
        enPreparacion = new EnPreparacion(pedidoMock);
    }



    @Test
    public void test001actualizarEstadoPasaAEnviado() {
        enPreparacion.actualizarEstado();
        
        verify(pedidoMock).setEstado(any(Enviado.class));
    }


    @Test
    public void test002cancelarPedidoReembolsaTodoYReponeStock() {
        when(pedidoMock.calcularTotalProductos()).thenReturn(500.0);
        when(pedidoMock.calcularCostoEnvio()).thenReturn(100.0);
        
        enPreparacion.cancelarPedido();
        
        verify(pedidoMock).reponerStock();
        verify(pedidoMock).registrarNotaDeCredito(600.0);
        verify(pedidoMock).setEstado(any(Cancelado.class));
    }


    @Test
    public void test003quitarItemLanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> enPreparacion.quitarItem(itemMock));
    }
    
    
    
}