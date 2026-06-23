package pago;

import pedido.Pedido;
import libreriasExternas.ApiBilleteraVirtual;



public class BilleteraVirtual extends MedioDePago {



    private ApiBilleteraVirtual api;



    public BilleteraVirtual(ApiBilleteraVirtual api) {
        this.api = api;
    }



    @Override
    protected void validarDatos(Pedido pedido) {
        double total = pedido.calcularTotalProductos() + pedido.calcularCostoEnvio();
        this.api.verificarSaldo(total);
    }


    @Override
    protected void reservarFondos(Pedido pedido) {
        double total = pedido.calcularTotalProductos() + pedido.calcularCostoEnvio();
        this.api.bloquearSaldo(total);
    }


    @Override
    protected void ejecutarTransaccion(Pedido pedido) {
        double total = pedido.calcularTotalProductos() + pedido.calcularCostoEnvio();
        this.api.acreditar(total);
    }


    @Override
    protected void notificarResultado(Pedido pedido) {
        System.out.println("Notificación push enviada");
    }
    
    
    
}