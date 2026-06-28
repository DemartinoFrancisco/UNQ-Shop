package notificaciones;

import pedido.Pedido;
import pedido.Estado;
import libreriasEInterfacesExternas.MailSender;
import pedido.Cancelado;



public class Fidelizacion implements Observador {



    private MailSender mailSender;



    public Fidelizacion(MailSender mailSender) {
        this.mailSender = mailSender;
    }



    @Override
    public void actualizar(Pedido pedido, Estado estadoAnterior, Estado estadoNuevo) {
        if (estadoNuevo instanceof Cancelado) {
            String destino = pedido.getCliente().getNombre() + "@email.com"; 
            String titulo = "Volve pronto!, te regalamos un 5% OFF";  // si se cancela un pedido, se le envía un mensaje con un cupón de descuento del 5%
            String mensaje = "Lamentamos que hayas cancelado. Usa el código DESCUENTO_5(cupón de descuento) en tu proxima compra.";
            
            this.mailSender.enviarMail(destino, titulo, mensaje, null);
        }
    }
    
    
    
}