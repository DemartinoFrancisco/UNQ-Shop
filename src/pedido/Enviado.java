package pedido;



public class Enviado extends Estado {



    public Enviado(Pedido pedido) {
        super(pedido);
    }



    @Override
    protected void actualizarEstado() {
        this.pedido.setEstado(new Entregado(this.pedido));
    }


    @Override
    protected void cancelarPedido() {
    	
        this.pedido.reponerStock();
        
        double montoAcreditar = this.pedido.calcularTotalProductos(); // solo se reembolsa la plata de los productos, el envío se lo cobramos igual.
        
        this.pedido.registrarNotaDeCredito(montoAcreditar);
        
        this.pedido.setEstado(new Cancelado(this.pedido));
    }
    
    
    
}