package Busqueda;

import Catalogo.Item;
import Catalogo.Producto;



public class PorCategoria implements Criterio {



    private String categoria;



    public PorCategoria(String categoria) {
        this.categoria = categoria;
    }



    @Override
    public boolean satisface(Item item) {
        if (item instanceof Producto) {
            Producto p = (Producto) item; // por ser java de tipado estatico, hay que avisarle que este es un producto en este caso. Si no, item no sabe que es getCategoria y explota tutto
            return p.validar() && p.getCategoria().equalsIgnoreCase(this.categoria); // "equalsIgnoreCase" sirve para no tener en cuenta mayusculas y minusculas.
        }
        return false; // si es un paquete, como los paquetes no tienen categoria, debe dar false.
    }
}