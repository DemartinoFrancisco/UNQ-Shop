package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class EnviadoTest {

	
	
    private Enviado enviado;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        
        enviado = new Enviado(pedidoMock);
    }



    @Test
    public void test001actualizarEstadoPasaAEntregado() {
        enviado.actualizarEstado();
        
        verify(pedidoMock).setEstado(any(Entregado.class));
    }


    @Test
    public void test002cancelarPedidoReembolsaSoloProductosYReponeStock() {
        when(pedidoMock.calcularTotalProductos()).thenReturn(1000.0);
        when(pedidoMock.calcularCostoEnvio()).thenReturn(300.0); // no se le reembolsa el pedido en este punto del ciclo de vida del pedido
        
        enviado.cancelarPedido();
        
        verify(pedidoMock).reponerStock();
        verify(pedidoMock).registrarNotaDeCredito(1000.0);
        verify(pedidoMock).setEstado(any(Cancelado.class)); // el any lo que hace es ver si a pedidoMock le mandaron una instancia de Cancelado 
    }
    
    
    
}