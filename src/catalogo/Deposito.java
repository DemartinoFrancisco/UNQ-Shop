package catalogo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pedido.OperacionInvalidaException;



public class Deposito {



    private Map<Item, Integer> stock;



    public Deposito() {
        this.stock = new HashMap<>();
    }



    public void registrarIngresoDeStock(Item item, int cantidad) {
        int stockActual = this.stock.getOrDefault(item, 0); // el default esta para el caso borde donde no esta registrado en el map el item buscado (apunta a Null), y si le preguntamos get a algo vacio explota. Por eso la solución es que si es vacio, le ponga de default que la cantidad de items de item es cero y asi no explota.
        this.stock.put(item, stockActual + cantidad);
    }


    public void agregarStock(List<Item> items) { // el metodo que llama el pedido cuando se cancela y devuelve los productos
        for (Item item : items) {
            this.registrarIngresoDeStock(item, 1); // Suma 1 al stock por cada aparición del ítem en la lista del pedido
        }
    }


    public void quitarStock(List<Item> items) {
        
        if (!this.tieneStockDeItems(items)) {
            throw new OperacionInvalidaException("Stock insuficiente para confirmar el pedido.");
        }
        
        // si llegamos hasta aca, ya comprobamos que hay suficiente stock para cada item que quiere el cliente, por lo que lo restamos sin problemas al stock actual del deposito
        for (Item item : items) {
            int stockActual = this.stock.getOrDefault(item, 0);
            this.stock.put(item, stockActual - 1);
        }
    }


    public boolean tieneStock(Item item) {
        return this.stock.getOrDefault(item, 0) > 0;
    }
    
    
    public boolean tieneStockDeItems(List<Item> items) {
    	
        Map<Item, Integer> cantidadesRequeridas = new HashMap<>();
        for (Item item : items) { // cuento que cantidad de cada item me están pidiendo en total
            int acumulado = cantidadesRequeridas.getOrDefault(item, 0);
            cantidadesRequeridas.put(item, acumulado + 1);
        }

        for (Map.Entry<Item, Integer> requerimiento : cantidadesRequeridas.entrySet()) { // con este for estoy diciendo: "de este map, dame todas las asociaciones de a una"
            Item itemPedido = requerimiento.getKey();
            int cantidadPedida = requerimiento.getValue();
            int stockDisponible = this.stock.getOrDefault(itemPedido, 0);
            
            if (stockDisponible < cantidadPedida) { // Comparamos la cantidad total de cada item requerido(o sea cuantos quiere el cliente) contra el stock real de cada item(cuantos items de esos que quiere el cliente hay en este deposito)
                return false; // con que falte uno solo de la lista, ya devuelve false
            }
        }

        return true;
    }
    
    
    
}