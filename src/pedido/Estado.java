package pedido;

import catalogo.Item;



public abstract class Estado {



    protected Pedido pedido;



    protected Estado(Pedido pedido) {
        this.pedido = pedido;
    }



    // Se usa Protected para que solo Pedido pueda disparar estas transiciones y no alguien de afuera de este patron state.
    
    protected void agregarItem(Item item) {
        throw new OperacionInvalidaException("No se pueden agregar ítems en este estado.");
    }


    protected void quitarItem(Item item) {
        throw new OperacionInvalidaException("No se pueden quitar ítems en este estado.");
    }


    protected void actualizarEstado() {
        throw new OperacionInvalidaException("No se puede avanzar el estado de este pedido.");
    }


    protected void cancelarPedido() {
        throw new OperacionInvalidaException("El pedido ya no se puede cancelar.");
    }
    
    
    
}