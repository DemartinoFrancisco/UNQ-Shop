package notificaciones;

import pedido.Pedido;
import pedido.Estado;



public interface Observador {



    public void actualizar(Pedido pedido, Estado estadoAnterior, Estado estadoNuevo);
    
    
    
}