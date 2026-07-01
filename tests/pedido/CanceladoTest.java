package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class CanceladoTest {

	
	
    private Cancelado cancelado;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        
        cancelado = new Cancelado(pedidoMock);
    }



    @Test
    public void test001actualizarEstadoLanzaExcepcionPorSerEstadoTerminal() {
        assertThrows(OperacionInvalidaException.class, () -> cancelado.actualizarEstado());
    }


    @Test
    public void test002cancelarPedidoLanzaExcepcionPorYaEstarCancelado() {
        assertThrows(OperacionInvalidaException.class, () -> cancelado.cancelarPedido());
    }
    
    
    
}