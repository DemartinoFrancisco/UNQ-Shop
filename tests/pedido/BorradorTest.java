package pedido;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import catalogo.Item;
import pago.Cliente;
import pago.MedioDePago;



public class BorradorTest {

	
	
    private Borrador borrador;
    private Pedido pedidoMock;
    private Item itemMock;
    private Cliente clienteMock;
    private MedioDePago medioPagoMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        clienteMock = mock(Cliente.class);
        medioPagoMock = mock(MedioDePago.class);
        
        when(pedidoMock.getCliente()).thenReturn(clienteMock);
        when(clienteMock.getMedioDePago()).thenReturn(medioPagoMock);
        
        borrador = new Borrador(pedidoMock);
    }



    @Test
    public void test001agregarItemDelegaEnPedidoInterno() {
        borrador.agregarItem(itemMock);
        
        verify(pedidoMock).agregarItemInterno(itemMock);
    }


    @Test
    public void test002quitarItemDelegaEnPedidoInterno() {
        borrador.quitarItem(itemMock);
        
        verify(pedidoMock).quitarItemInterno(itemMock);
    }


    @Test
    public void test003actualizarEstadoPasaAConfirmadoYProcesaPagoYStock() {
        borrador.actualizarEstado();
        
        verify(pedidoMock).setFecha(any(LocalDate.class));
        verify(medioPagoMock).procesarPago(pedidoMock);
        verify(pedidoMock).decrementarStock();
        verify(pedidoMock).setEstado(any(Confirmado.class));
    }


    @Test
    public void test004cancelarPedidoPasaACancelado() {
        borrador.cancelarPedido();
        
        verify(pedidoMock).setEstado(any(Cancelado.class));
    }
    
    
    
}