package envio;

import Pedido.Pedido;
import libreriasExternas.EnvioExpress;



public class Express implements Estrategia {



    @Override
    public double calcularCostoDeEnvio(Pedido pedido, String direccion) {
        double precioTotal = pedido.getItems().stream()
                                              .mapToDouble(item -> item.getPrecioFinal())
                                              .sum();
        
        return (double) EnvioExpress.calcularCosto((float) precioTotal); // "(double)" y "(float)" se llaman casteos y sirven para devolver el tipo que queremos, al castear de un double a float,se perderan los decimales.
    }
    
    
    @Override
    public String estimarDiasDeEntrega(Pedido pedido) {
        return "1 día hábil";
    }
    
    
    
}