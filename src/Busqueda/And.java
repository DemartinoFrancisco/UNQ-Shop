package Busqueda;

import java.util.List;

import Catalogo.Item;



public class And implements Criterio {



    private List<Criterio> criterios;



    public And(List<Criterio> criterios) {
        this.criterios = criterios;
    }



    @Override
    public boolean satisface(Item item) {
        return this.criterios.stream()
                             .allMatch(criterio -> criterio.satisface(item));
    }
    
    
  
}