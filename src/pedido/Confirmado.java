package pedido;



public class Confirmado extends Estado {



    public Confirmado(Pedido pedido) {
        super(pedido);
    }



    @Override
    protected void actualizarEstado() {
        this.pedido.setEstado(new EnPreparacion(this.pedido));
    }


    @Override
    protected void cancelarPedido() {
        this.pedido.reponerStock();
        
        double montoAcreditar = this.pedido.calcularTotalProductos() + this.pedido.calcularCostoEnvio();  // se devuelve la plata de los productos y del envio porque todavía no salió a la calle.
        
        this.pedido.registrarNotaDeCredito(montoAcreditar);
        
        this.pedido.setEstado(new Cancelado(this.pedido));
    }
    
    
    
}