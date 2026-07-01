package pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class EstadoTest {

	
	
    private Estado estadoAnonimo;
    private Pedido pedidoMock;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        
        estadoAnonimo = new Estado(pedidoMock) {};  // Instancio una subclase anonima para probar los métodos base de la clase abstracta
    }



    @Test
    public void test001agregarItemLanzaExcepcionPorDefecto() {
        assertThrows(OperacionInvalidaException.class, () -> estadoAnonimo.agregarItem(itemMock));
    }


    @Test
    public void test002quitarItemLanzaExcepcionPorDefecto() {
        assertThrows(OperacionInvalidaException.class, () -> estadoAnonimo.quitarItem(itemMock));
    }


    @Test
    public void test003actualizarEstadoLanzaExcepcionPorDefecto() {
        assertThrows(OperacionInvalidaException.class, () -> estadoAnonimo.actualizarEstado());
    }


    @Test
    public void test004cancelarPedidoLanzaExcepcionPorDefecto() {
        assertThrows(OperacionInvalidaException.class, () -> estadoAnonimo.cancelarPedido());
    }
    
    
    
}