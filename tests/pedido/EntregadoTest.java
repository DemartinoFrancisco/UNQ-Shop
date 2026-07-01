package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class EntregadoTest {

	
	
    private Entregado entregado;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        
        entregado = new Entregado(pedidoMock);
    }



    @Test
    public void test001actualizarEstadoLanzaExcepcionPorSerEstadoTerminal() {
        assertThrows(OperacionInvalidaException.class, () -> entregado.actualizarEstado()); // con "OperacionInvalidaException.class" le digo a java, "el código va a explotar, pero la explosión tiene que ser específicamente de este tipo" ; el segundo parametro es la acción a ejecutar que devuelve el error
    }


    @Test
    public void test002cancelarPedidoLanzaExcepcionPorSerEstadoTerminal() {
        assertThrows(OperacionInvalidaException.class, () -> entregado.cancelarPedido());
    }
    
    
    
}