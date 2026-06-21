package busqueda;

import catalogo.Item;



public class Not implements Criterio {



    private Criterio criterioEnvuelto;



    public Not(Criterio criterioEnvuelto) {
        this.criterioEnvuelto = criterioEnvuelto;
    }



    @Override
    public boolean satisface(Item item) {
        return !this.criterioEnvuelto.satisface(item);
    }
    
    
    
}