package catalogo;

import java.util.List;



public class Paquete extends Item {

	
	
    private List<Item> items;

  
    
    public Paquete(String nombre, String descripcion, double precioBase, double peso, double descuento, List<Item> items) {
        super(nombre, descripcion, precioBase, peso, descuento);
        this.items = items;
    }

    
    
    @Override
    public double getPrecioFinal() {
        double totalItems = this.items.stream()
                                            .mapToDouble(item -> item.getPrecioFinal())
                                            .sum();
        
        return totalItems * (1 - this.getDescuento());
    }

    
    @Override
    public boolean validar() {
        boolean baseValida = super.validar();
        boolean itemsValidos = this.items.stream().allMatch(item -> item.validar());

        return baseValida && itemsValidos;
    }

    
    public List<Item> getItems() {
        return this.items;
    }
    
    
    
}