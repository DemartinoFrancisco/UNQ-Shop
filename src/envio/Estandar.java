package envio;

import Pedido.Pedido;
import libreriasExternas.CorreoArgentina;
import libreriasExternas.Direccion;



public class Estandar implements Estrategia {



    @Override
    public double calcularCostoDeEnvio(Pedido pedido, String direccion) {
        double pesoTotal = pedido.getItems().stream()
                                            .mapToDouble(item -> item.getPeso())
                                            .sum();
        
        Direccion direccionEnvio = new Direccion(direccion);
        
        return (double) CorreoArgentina.estimarEnvio((float) pesoTotal, direccionEnvio); // "(double)" y "(float)" se llaman casteos y sirven para devolver el tipo que queremos, al castear de un double a float,se perderan los decimales. 
    }
    
    
    @Override
    public String estimarDiasDeEntrega(Pedido pedido) {
        return "Entre 5 y 7 días hábiles";
    }
    
    
    
}