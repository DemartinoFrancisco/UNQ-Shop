package notificaciones;

import pedido.Pedido;
import pedido.Estado;
import libreriasEInterfacesExternas.MailSender;
import pedido.Entregado;



public class GeneradorFactura implements Observador {



    private MailSender mailSender;



    public GeneradorFactura(MailSender mailSender) {
        this.mailSender = mailSender;
    }



    @Override
    public void actualizar(Pedido pedido, Estado estadoAnterior, Estado estadoNuevo) {
        if (estadoNuevo instanceof Entregado) {
            String destino = pedido.getCliente().getNombre() + "@email.com"; 
            String titulo = "Factura de su compra";
            String mensaje = "Adjuntamos el comprobante fiscal de su pedido.";
            String adjunto = "factura_" + pedido.getCliente().getNombre() + ".pdf";
            
            this.mailSender.enviarMail(destino, titulo, mensaje, adjunto);
        }
    }
    
    
    
}