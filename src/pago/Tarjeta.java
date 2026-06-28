package pago;

import pedido.Pedido;

import java.time.LocalDate;

import libreriasEInterfacesExternas.ApiTarjeta;



public class Tarjeta extends MedioDePago {



    private ApiTarjeta api;
    private int numero;
    private int cvv;
    private LocalDate vencimiento;



    public Tarjeta(ApiTarjeta api, int numero, int cvv, LocalDate vencimiento) {
        this.api = api;
        this.numero = numero;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
    }



    @Override
    protected void validarDatos(Pedido pedido) {
        this.api.validar(this.numero, this.cvv, this.vencimiento);
    }


    @Override
    protected void reservarFondos(Pedido pedido) {
        double total = pedido.calcularTotalProductos() + pedido.calcularCostoEnvio();
        this.api.preAutorizar(total);
    }


    @Override
    protected void ejecutarTransaccion(Pedido pedido) {
        double total = pedido.calcularTotalProductos() + pedido.calcularCostoEnvio();
        this.api.transferir(total);
    }


    @Override
    protected void notificarResultado(Pedido pedido) {
        System.out.println("Generando y registrando Cupón de pago imprimible...");
    }
}