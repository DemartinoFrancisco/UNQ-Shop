package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class ConfirmadoTest {

	
	
    private Confirmado confirmado;
    private Pedido pedidoMock;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        
        confirmado = new Confirmado(pedidoMock);
    }



    @Test
    public void test001actualizarEstadoPasaAEnPreparacion() {
        confirmado.actualizarEstado();
        
        verify(pedidoMock).setEstado(any(EnPreparacion.class));
    }


    @Test
    public void test002cancelarPedidoReembolsaTodoYReponeStock() {
        when(pedidoMock.calcularTotalProductos()).thenReturn(1000.0);
        when(pedidoMock.calcularCostoEnvio()).thenReturn(200.0);
        
        confirmado.cancelarPedido();
        
        verify(pedidoMock).reponerStock();
        verify(pedidoMock).registrarNotaDeCredito(1200.0);
        verify(pedidoMock).setEstado(any(Cancelado.class));
    }


    @Test
    public void test003agregarItemLanzaExcepcionPorEstarConfirmado() {
        assertThrows(OperacionInvalidaException.class, () -> confirmado.agregarItem(itemMock));
    }
    
    
    
}