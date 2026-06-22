package envio;

import pedido.Pedido;



public interface Estrategia {



    public double calcularCostoDeEnvio(Pedido pedido, String direccion);
    
    
    public String estimarDiasDeEntrega(Pedido pedido);
    
    
    
}