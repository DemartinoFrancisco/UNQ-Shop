package pago;

import libreriasEInterfacesExternas.ApiTransferencia;
import pedido.Pedido;



public class Transferencia extends MedioDePago {



    private ApiTransferencia api;
    private int cbu;
    private String alias;



    public Transferencia(ApiTransferencia api, int cbu, String alias) {
        this.api = api;
        this.cbu = cbu;
        this.alias = alias;
    }



    @Override
    protected void validarDatos(Pedido pedido) {
        this.api.validar(this.cbu, this.alias);
    }


    @Override
    protected void reservarFondos(Pedido pedido) {
        // El enunciado dice: "No aplica (transferencia directa)", por lo que lo dejo vacio.
    }


    @Override
    protected void ejecutarTransaccion(Pedido pedido) {
        double total = pedido.calcularTotalProductos() + pedido.calcularCostoEnvio();
        this.api.transferirInmediato(total);
    }


    @Override
    protected void notificarResultado(Pedido pedido) {
        System.out.println("Generando y registrando Comprobante CBU con número de operación...");
    }
}