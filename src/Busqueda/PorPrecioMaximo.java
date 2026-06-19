package Busqueda;

import Catalogo.Item;



public class PorPrecioMaximo implements Criterio {



    private double precioMax;



    public PorPrecioMaximo(double precioMax) {
        this.precioMax = precioMax;
    }



    @Override
    public boolean satisface(Item item) {
        return item.getPrecioBase() <= this.precioMax;
    }
    
    
    
}