package envio;

import Pedido.Pedido;
import item.Deposito;



public class Sucursal implements Estrategia {



    private Deposito deposito;



    public Sucursal(Deposito deposito) {
        this.deposito = deposito;
    }

    

    @Override
    public double calcularCostoDeEnvio(Pedido pedido, String direccion) {
        return 0.0;
    }


    @Override
    public String estimarDiasDeEntrega(Pedido pedido) {
    	
        boolean hayStock = this.deposito.tieneStock(pedido.getItems());
        
        if (hayStock) {
            return "Inmediato";
        } else {
            return "Hasta 3 días";
        }
    }
    
    
    
}