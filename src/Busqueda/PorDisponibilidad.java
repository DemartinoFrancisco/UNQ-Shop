package Busqueda;

import java.util.List;

import Item.Item;



public class PorDisponibilidad implements Criterio {



    private List<Deposito> depositos;



    public PorDisponibilidad(List<Deposito> depositos) {
        this.depositos = depositos;
    }



    @Override
    public boolean satisface(Item item) {
        return this.depositos.stream()
                             .anyMatch(deposito -> deposito.tieneStock(item));
    }
    
    
    
}