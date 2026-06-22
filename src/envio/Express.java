package envio;

import libreriasExternas.EnvioExpress;
import pedido.Pedido;



public class Express implements Estrategia {


// Si no escribis ningún constructor en tu clase, Java te crea uno "invisible" y vacío por defecto( y como ni estrategiaDeEnvio ni esta clase tienen atributos, no hay nada que inicializar tampoco ).
	
	
	
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