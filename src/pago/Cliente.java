package pago;

import java.util.ArrayList;
import java.util.List;



public class Cliente {



    private String nombre;
    private String direccion;
    private MedioDePago formaDePago;
    private List<Double> notasDeCredito;



    public Cliente(String nombre, String direccion, MedioDePago formaDePago) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.formaDePago = formaDePago;
        this.notasDeCredito = new ArrayList<>();
    }



    public MedioDePago getMedioDePago() {
        return this.formaDePago;
    }


    public String getDireccion() {
        return this.direccion;
    }
    
    
    public String getNombre() {
    	return this.nombre;
    }


    public void agregarNotaDeCredito(double monto) {
        this.notasDeCredito.add(monto);
    }
    
    
    
}