package pago;

import pedido.Pedido;



public abstract class MedioDePago {



    public final void procesarPago(Pedido pedido) { // En el patron template method, el esqueleto es inalterable, por eso es final
        this.validarDatos(pedido);
        this.reservarFondos(pedido);
        this.ejecutarTransaccion(pedido);
        this.notificarResultado(pedido);
    }



    protected abstract void validarDatos(Pedido pedido);


    protected abstract void reservarFondos(Pedido pedido);


    protected abstract void ejecutarTransaccion(Pedido pedido);


    protected void notificarResultado(Pedido pedido) {
        System.out.println("Transacción registrada en el sistema."); // implementación por defecto
    }
    
    
    
}