package pedido;



public class EnPreparacion extends Estado {



    public EnPreparacion(Pedido pedido) {
        super(pedido);
    }



    @Override
    protected void actualizarEstado() {
        this.pedido.setEstado(new Enviado(this.pedido));
    }


    @Override
    protected void cancelarPedido() {
        this.pedido.reponerStock();
        
        double montoAcreditar = this.pedido.calcularTotalProductos() + this.pedido.calcularCostoEnvio(); // reembolsa productos y envio
        
        this.pedido.registrarNotaDeCredito(montoAcreditar);
        
        this.pedido.setEstado(new Cancelado(this.pedido));
    }
    
    
    
}