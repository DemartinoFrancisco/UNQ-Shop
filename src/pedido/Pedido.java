package pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import catalogo.Deposito;
import catalogo.Item;
import envio.EstrategiaDeEnvio;
import pago.Cliente;
import notificaciones.Observable;



public class Pedido extends Observable {



    private Estado estado;
    private List<Item> items;
    private LocalDate fecha;
    private Cliente cliente;
    private EstrategiaDeEnvio metodoDeEnvio;
    private Deposito deposito;


 // mensajes publicos (Los que usa el usuario desde el main por ejemplo)
    
    public Pedido(Cliente cliente, EstrategiaDeEnvio metodoDeEnvio, Deposito deposito) {
    	super();
        this.cliente = cliente;
        this.metodoDeEnvio = metodoDeEnvio;
        this.deposito = deposito;
        this.items = new ArrayList<>();
        this.estado = new Borrador(this); 
    }

    

    public void agregarItem(Item item) {
        this.estado.agregarItem(item); // el estado se encarga de ver si es válido agregar un elemento en este estado, si lo es usa el mensaje protected llamado "agregarItemInterno"
    }


    public void quitarItem(Item item) {
        this.estado.quitarItem(item);
    }


    public void actualizarEstado() {
        this.estado.actualizarEstado(); // le envia al estado que actualice su estado, si es entregado o cancelado tira un error, si no hacen las cosas que tienen que hacer y terminan llamando a setEstado(...)
    }


    public void cancelarPedido() {
        this.estado.cancelarPedido();
    }


    public List<Item> getItems() {
        return this.items;
    }
    
    
    public double calcularTotalProductos() {
        return this.items.stream()
                         .mapToDouble(item -> item.getPrecioFinal())
                         .sum();
    }


    public double calcularCostoEnvio() {
        return this.metodoDeEnvio.calcularCostoDeEnvio(this, this.cliente.getDireccion());
    }
    
    
    public Cliente getCliente() {
        return this.cliente;
    }
    
    
    public LocalDate getFecha() {
    	return this.fecha;
    }
    
    
    public Estado getEstado() {
        return this.estado;
    }



 // mensajes protected (Exclusivos para que los usen las clases concretas estado)
    
    protected void setEstado(Estado nuevoEstado) {
        Estado estadoAnterior = this.estado;
        this.estado = nuevoEstado;
        
        this.notificarObservadores(this, estadoAnterior, nuevoEstado);
    }


    protected void agregarItemInterno(Item item) {
        this.items.add(item); // los estados no tocan directamente los atributos de la clase Pedido, los modifican indirectamente al mandarle un mensaje a Pedido para que los cambie.
        
    }


    protected void quitarItemInterno(Item item) {
        this.items.remove(item);
    }


    protected void decrementarStock() {
        this.deposito.quitarStock(this.items);
    }


    protected void reponerStock() {
        this.deposito.agregarStock(this.items);
    }


    protected void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    


    protected void registrarNotaDeCredito(double monto) {
        this.cliente.agregarNotaDeCredito(monto);
    }

    

}