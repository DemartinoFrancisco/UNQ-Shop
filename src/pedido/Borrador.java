package pedido;

import catalogo.Item;
import java.time.LocalDate;



public class Borrador extends Estado {



    public Borrador(Pedido pedido) {
        super(pedido);
    }



    @Override
    protected void agregarItem(Item item) {
        this.pedido.agregarItemInterno(item);
    }


    @Override
    protected void quitarItem(Item item) {
        this.pedido.quitarItemInterno(item);
    }


    @Override
    protected void actualizarEstado() {
        this.pedido.setFecha(LocalDate.now());
        
        this.pedido.getCliente().getMedioDePago().procesarPago(this.pedido);
        
        this.pedido.decrementarStock();
        
        this.pedido.setEstado(new Confirmado(this.pedido));
    }


    @Override
    protected void cancelarPedido() {
        this.pedido.setEstado(new Cancelado(this.pedido));
    }
    
    
    
}