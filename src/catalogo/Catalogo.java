package catalogo;

import java.util.List;
import java.util.stream.Collectors;

import busqueda.Criterio;



public class Catalogo {



    private List<Item> items;



    public Catalogo(List<Item> items) {
        this.items = items;
    }



    public List<Item> buscar(Criterio criterio) {
        return this.items.stream()
                         .filter(item -> criterio.satisface(item))
                         .collect(Collectors.toList());
    }

    
    
}