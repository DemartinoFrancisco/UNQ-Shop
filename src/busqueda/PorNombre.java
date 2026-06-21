package busqueda;

import catalogo.Item;



public class PorNombre implements Criterio {



    private String textoABuscar;



    public PorNombre(String textoABuscar) {
        this.textoABuscar = textoABuscar;
    }



    @Override
    public boolean satisface(Item item) {
        //se pasan ambos strings a minusculas asi no se tienen en cuenta mayusculas y minusculas.
    	return item.getNombre().toLowerCase().contains(this.textoABuscar.toLowerCase());
    }
    
    
    
}