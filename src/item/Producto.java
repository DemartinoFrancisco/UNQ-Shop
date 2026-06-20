package item;



public class Producto extends Item {

	
	
    private Integer sku; 
    private String categoria;
    private String marca;

    
    
    public Producto(String nombre, String descripcion, double precioBase, double peso, double descuento, Integer sku, String categoria, String marca) {
        super(nombre, descripcion, precioBase, peso, descuento);
        this.sku = sku;
        this.categoria = categoria;
        this.marca = marca;
    }

    
    
    @Override
    public double getPrecioFinal() {
        return this.getPrecioBase() * (1 - this.getDescuento());
    }

    
    @Override
    public boolean validar() {
        boolean baseValida = super.validar();
        boolean skuValido = this.sku != null && this.sku > 0;
        boolean catValida = this.categoria != null;
        boolean marcaValida = this.marca != null;

        return baseValida && skuValido && catValida && marcaValida;
    }

    
    public Integer getSKU() {
        return this.sku;
    }

    
    public String getCategoria() {
        return this.categoria;
    }
    
    
    
}