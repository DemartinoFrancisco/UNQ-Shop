package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import libreriasEInterfacesExternas.MailSender;
import pedido.Pedido;
import pedido.Cancelado;
import pedido.Entregado;
import pago.Cliente;



public class FidelizacionTest {

	
	
    private Fidelizacion fidelizacion;
    private MailSender mailSenderMock;
    private Pedido pedidoMock;
    private Cliente clienteMock;
    private Cancelado estadoCanceladoMock;
    private Entregado estadoEntregadoMock;

    
    
    @BeforeEach
    public void setUp() {
        mailSenderMock = mock(MailSender.class);
        pedidoMock = mock(Pedido.class);
        clienteMock = mock(Cliente.class);
        estadoCanceladoMock = mock(Cancelado.class);
        estadoEntregadoMock = mock(Entregado.class);

        when(pedidoMock.getCliente()).thenReturn(clienteMock);
        when(clienteMock.getNombre()).thenReturn("Juan");

        fidelizacion = new Fidelizacion(mailSenderMock);
    }



    @Test
    public void test001NotificaConCuponSiEstadoNuevoEsCancelado() {
        fidelizacion.actualizar(pedidoMock, estadoEntregadoMock, estadoCanceladoMock);
        
        verify(mailSenderMock).enviarMail(
            "Juan@email.com",
            "Volve pronto!, te regalamos un 5% OFF",
            "Lamentamos que hayas cancelado. Usa el código DESCUENTO_5(cupón de descuento) en tu proxima compra.",
            null
        );
    }


    @Test
    public void test002NoNotificaSiEstadoNuevoNoEsCancelado() {
        fidelizacion.actualizar(pedidoMock, estadoCanceladoMock, estadoEntregadoMock);
        
        verify(mailSenderMock, never()).enviarMail(anyString(), anyString(), anyString(), any());
    }
    
    
    
}