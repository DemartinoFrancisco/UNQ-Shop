package Busqueda;

import java.util.List;

import Catalogo.Item;



public class Or implements Criterio {



    private List<Criterio> criterios;



    public Or(List<Criterio> criterios) {
        this.criterios = criterios;
    }



    @Override
    public boolean satisface(Item item) {
        return this.criterios.stream()
                             .anyMatch(criterio -> criterio.satisface(item));
    }
    
    
    
}