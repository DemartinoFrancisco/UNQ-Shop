package catalogo;

import java.util.HashMap;
import java.util.Map;



public abstract class Item {

	
	
    private String nombre;
    private String descripcion;
    private double precioBase;
    protected double descuento;
    private double peso;
    private Map<String, Object> atributosDinamicos; // Los tipos primitivos como int o string si bien no son objects, al ponerlos en este map donde iria un object, java se da cuenta de que necesitas un object y no un tipo primitivo,
    												// y lo "envuelven" en su clase wrapper (integer por ejemplo es la clase wrapper de int(su único atributo es un int pero tiene muchas más funcionalidades y puede recibir mensajes)).

    
    
    public Item(String nombre, String descripcion, double precioBase, double peso, double descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.descuento = descuento;
        this.peso = peso;
        this.atributosDinamicos = new HashMap<>();
    }

    
    
    public void addAtributo(String attribute, Object data) {
        this.atributosDinamicos.put(attribute, data);
    }

    
    public Object getAtributo(String attribute) {
        return this.atributosDinamicos.get(attribute);
    }

    
    protected Map<String, Object> getAsociacionDeAtributo() {
        return this.atributosDinamicos;
    }

    
    public String getNombre() {
        return this.nombre;
    }

    
    public String getDescripcion() {
        return this.descripcion;
    }

    
    public double getPrecioBase() {
        return this.precioBase;
    }

    
    public double getDescuento() {
        return this.descuento;
    }
    
    
    public double getPeso() {
    	return this.peso;
    }
    

    
    public abstract double getPrecioFinal();
    
    
    public boolean validar() {
        boolean nombreValido = this.nombre != null;
        boolean descValida = this.descripcion != null;
        boolean precioValido = this.precioBase > 0;
        boolean pesoValido = this.peso > 0;
        boolean dinamicosValidos = !this.atributosDinamicos.containsValue(null);

        return nombreValido && descValida && precioValido && pesoValido && dinamicosValidos;
    }
    
    
    
}