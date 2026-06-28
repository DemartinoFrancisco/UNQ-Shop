package catalogo;

import java.util.HashMap;
import java.util.Map;

import reportes.ReporteVisitor;



public abstract class Item {

	
	
    private String nombre;
    private String descripcion;
    private double precioBase;
    protected double descuento;
    private double peso;
    private Map<String, Object> atributosDinamicos; // Los tipos primitivos como int o string si bien no son objects, al ponerlos en este map donde iria un object, java se da cuenta de que necesitas un object y no un tipo primitivo,
    												// y lo "envuelven" en su clase wrapper (integer por ejemplo es la clase wrapper de int(su único atributo es un int pero tiene muchas más funcionalidades y puede recibir mensajes)).

    
    
    protected Item(String nombre, String descripcion, double precioBase, double peso, double descuento) { // aunque sea una clase abstracta le ponemos un constructor para que sus subclases puedan inicializar más facilmente sus atributos compartidos. Como solo queremos que lo usen las subclases lo ponemos como protected
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.descuento = descuento;
        this.peso = peso;
        this.atributosDinamicos = new HashMap<>(); // HashMap para los maps es como el arrayList para las lists
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
    
    
    public abstract void accept(ReporteVisitor visitor) ;
    
    
    @Override
    public boolean equals(Object obj) { // le digo al item como se compara con otros objetos, sirve para los reportes
        
    	if (this == obj) return true; // si ocupan el mismo espacio en memoria son el mismo objeto
        
        if (obj == null || this.getClass() != obj.getClass()) return false; // si pasan un objeto null como parametro ya sabemos que no es igual que un item que si ocupa espacio en memoria ; la segunda parte se fija si ambos objetos son instancias de la clase Item
        
        Item otroItem = (Item) obj; // cómo ya confirmamos en la linea anterior que el objeto pasado como parametro es un item, lo "casteamos" para que sea de tipo "Item" de forma oficial y poder mandarle los mismos mensajes que le mandariamos a un item
        
        return this.nombre.equals(otroItem.nombre); // si 2 items tienen exactamente el mismo nombre, en mi empresa son el mismo item, me gustaria hacerlo por SKU, pero los paquetes no tienen SKU sadly.
    }
    
    
}