package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import libreriasEInterfacesExternas.MailSender;
import pedido.Pedido;
import pedido.Confirmado;
import pedido.Enviado;
import pedido.Entregado;
import pedido.Cancelado;
import pago.Cliente;



public class NotificadorEmailTest {

	
	
    private NotificadorEmail notificador;
    private MailSender mailSenderMock;
    private Pedido pedidoMock;
    private Cliente clienteMock;
    private Confirmado estadoConfirmadoMock;
    private Enviado estadoEnviadoMock;
    private Entregado estadoEntregadoMock;
    private Cancelado estadoCanceladoMock;

    
    
    @BeforeEach
    public void setUp() {
        mailSenderMock = mock(MailSender.class);
        pedidoMock = mock(Pedido.class);
        clienteMock = mock(Cliente.class);
        estadoConfirmadoMock = mock(Confirmado.class);
        estadoEnviadoMock = mock(Enviado.class);
        estadoEntregadoMock = mock(Entregado.class);
        estadoCanceladoMock = mock(Cancelado.class);

        when(pedidoMock.getCliente()).thenReturn(clienteMock);
        when(clienteMock.getNombre()).thenReturn("Carlos");

        notificador = new NotificadorEmail(mailSenderMock);
    }



    @Test
    public void test001NotificaSiEstadoEsConfirmado() {
        notificador.actualizar(pedidoMock, null, estadoConfirmadoMock);
        
        verify(mailSenderMock).enviarMail(
            eq("Carlos@email.com"),
            eq("Actualización de su pedido"),
            startsWith("Su pedido cambió a: "),
            isNull()
        );
    }


    @Test
    public void test002NotificaSiEstadoEsEnviado() {
        notificador.actualizar(pedidoMock, estadoConfirmadoMock, estadoEnviadoMock);
        
        verify(mailSenderMock).enviarMail( // se fija si el mailSender recibio un mensaje con exactamente esos 3 strings y un null en el cuarto parametro
            eq("Carlos@email.com"),
            eq("Actualización de su pedido"),
            startsWith("Su pedido cambió a: "),
            isNull()
        );
    }


    @Test
    public void test003NotificaSiEstadoEsEntregado() {
        notificador.actualizar(pedidoMock, estadoEnviadoMock, estadoEntregadoMock);
        
        verify(mailSenderMock).enviarMail(
            eq("Carlos@email.com"),
            eq("Actualización de su pedido"),
            startsWith("Su pedido cambió a: "),
            isNull()
        );
    }


    @Test
    public void test004NoNotificaSiEstadoEsBorradorOCancelado() {
        notificador.actualizar(pedidoMock, estadoConfirmadoMock, estadoCanceladoMock);
        
        verify(mailSenderMock, never()).enviarMail(anyString(), anyString(), anyString(), any());
    }
    
    
    
}