package notificaciones;

import pedido.Pedido;
import pedido.Estado;
import libreriasEInterfacesExternas.MailSender;
import pedido.Confirmado;
import pedido.Enviado;
import pedido.Entregado;



public class NotificadorEmail implements Observador {



    private MailSender mailSender;



    public NotificadorEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }



    @Override
    public void actualizar(Pedido pedido, Estado estadoAnterior, Estado estadoNuevo) {
        if (estadoNuevo instanceof Confirmado || estadoNuevo instanceof Enviado || estadoNuevo instanceof Entregado) { // Solo actúa ante las transiciones CONFIRMADO, ENVIADO y ENTREGADO
            
            String destino = pedido.getCliente().getNombre() + "@email.com"; 
            String titulo = "Actualización de su pedido";
            String mensaje = "Su pedido cambió a: " + estadoNuevo.getClass().getSimpleName();
            
            this.mailSender.enviarMail(destino, titulo, mensaje, null);
        }
    }
    
    
    
}