package notificaciones;

import java.util.ArrayList;
import java.util.List;
import pedido.Pedido;
import pedido.Estado;



public abstract class Observable {



    private List<Observador> observadores;



    protected Observable() {
        this.observadores = new ArrayList<>();
    }



    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }


    public void eliminarObservador(Observador observador) {
        this.observadores.remove(observador);
    }


    protected void notificarObservadores(Pedido pedido, Estado estadoAnterior, Estado estadoNuevo) {
        for (Observador obs : this.observadores) {
            obs.actualizar(pedido, estadoAnterior, estadoNuevo);
        }
    }
    
    
    
}