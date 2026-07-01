package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.Estado;



public class ObservableTest {

	
	
    private Observable observableSUT;
    private Observador observadorMock1;
    private Observador observadorMock2;
    private Pedido pedidoMock;
    private Estado estadoAnteriorMock;
    private Estado estadoNuevoMock;

    
    
    @BeforeEach
    public void setUp() {
        observadorMock1 = mock(Observador.class);
        observadorMock2 = mock(Observador.class);
        pedidoMock = mock(Pedido.class);
        estadoAnteriorMock = mock(Estado.class);
        estadoNuevoMock = mock(Estado.class);

        observableSUT = new Observable() {};
    }



    @Test
    public void test001AgregaYNotificaATodosLosObservadores() {
        observableSUT.agregarObservador(observadorMock1);
        observableSUT.agregarObservador(observadorMock2);
        
        observableSUT.notificarObservadores(pedidoMock, estadoAnteriorMock, estadoNuevoMock);
        
        verify(observadorMock1, times(1)).actualizar(pedidoMock, estadoAnteriorMock, estadoNuevoMock);
        verify(observadorMock2, times(1)).actualizar(pedidoMock, estadoAnteriorMock, estadoNuevoMock);
    }


    @Test
    public void test002EliminaObservadorYNoLoNotifica() {
        observableSUT.agregarObservador(observadorMock1);
        observableSUT.agregarObservador(observadorMock2);
        observableSUT.eliminarObservador(observadorMock1);
        
        observableSUT.notificarObservadores(pedidoMock, estadoAnteriorMock, estadoNuevoMock);
        
        verify(observadorMock1, never()).actualizar(any(), any(), any());
        verify(observadorMock2, times(1)).actualizar(pedidoMock, estadoAnteriorMock, estadoNuevoMock);
    }
    
    
    
}