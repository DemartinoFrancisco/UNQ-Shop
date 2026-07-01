package notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import libreriasEInterfacesExternas.MailSender;
import pedido.Pedido;
import pedido.Entregado;
import pedido.Confirmado;
import pago.Cliente;



public class GeneradorFacturaTest {

	
	
    private GeneradorFactura generadorFactura;
    private MailSender mailSenderMock;
    private Pedido pedidoMock;
    private Cliente clienteMock;
    private Entregado estadoEntregadoMock;
    private Confirmado estadoConfirmadoMock;

    
    
    @BeforeEach
    public void setUp() {
        mailSenderMock = mock(MailSender.class);
        pedidoMock = mock(Pedido.class);
        clienteMock = mock(Cliente.class);
        estadoEntregadoMock = mock(Entregado.class);
        estadoConfirmadoMock = mock(Confirmado.class);

        when(pedidoMock.getCliente()).thenReturn(clienteMock);
        when(clienteMock.getNombre()).thenReturn("Maria");

        generadorFactura = new GeneradorFactura(mailSenderMock);
    }



    @Test
    public void test001EnviaFacturaSiEstadoNuevoEsEntregado() {
        generadorFactura.actualizar(pedidoMock, estadoConfirmadoMock, estadoEntregadoMock);
        
        verify(mailSenderMock).enviarMail(
            "Maria@email.com",
            "Factura de su compra",
            "Adjuntamos el comprobante fiscal de su pedido.",
            "factura_Maria.pdf"
        );
    }


    @Test
    public void test002NoEnviaFacturaSiEstadoNuevoNoEsEntregado() {
        generadorFactura.actualizar(pedidoMock, estadoEntregadoMock, estadoConfirmadoMock);
        
        verify(mailSenderMock, never()).enviarMail(anyString(), anyString(), anyString(), any());
    }
    
    
    
}