package reportes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pedido.Pedido;
import pedido.Entregado;
import catalogo.Item;
import catalogo.Producto;
import catalogo.Paquete;



public class ReporteDeProductosMasVendidos implements ReporteVisitor {



    private List<Pedido> pedidosHistoricos;
    private Map<Item, Integer> cantidadesVendidas;
    private Map<Item, Double> recaudadoPorItem;



    public ReporteDeProductosMasVendidos(List<Pedido> todosLosPedidos, LocalDate inicio, LocalDate fin) {
        this.pedidosHistoricos = todosLosPedidos.stream()
                .filter(p -> !p.getFecha().isBefore(inicio) && !p.getFecha().isAfter(fin)) // filtramos para quedarnos solo con los pedidos que entraron en el rango de fechas solicitado
                .filter(p -> p.getEstado() instanceof Entregado) // filtramos para quedarnose solo con los pedidos que tienen estado entregado, o sea que son ventas ya concretadas con seguridad
                .collect(Collectors.toList());
                
        this.cantidadesVendidas = new HashMap<>();
        this.recaudadoPorItem = new HashMap<>();
    }



    @Override
    public void visitarProducto(Producto producto) {
        this.procesarItem(producto);
    }


    @Override
    public void visitarPaquete(Paquete paquete) {
        this.procesarItem(paquete);
    }


    private void procesarItem(Item item) { // lo hice una subtarea ya que para contar cuantas veces aparece un ítem en una lista, la lógica es exactamente la misma sin importar si es un producto o un paquete.
        int cantidad = 0;
        double recaudado = 0.0;
        
        for (Pedido pedido : this.pedidosHistoricos) {
            for (Item itemPedido : pedido.getItems()) { // recorrido por cada item de cada pedido en el periodo de tiempo elegido
                if (itemPedido.equals(item)) { // si el item actual es igual al item que se esta procesando( o sea si encontro en la lista de items de los pedidos un item como el que se esta buscando) suma 1 y lo agrega a lo recaudado por ese item
                    cantidad++;
                    recaudado += itemPedido.getPrecioFinal(); 
                }
            }
        }
        
        if (cantidad > 0) {  // solo agregamos el item pasado como parametro al reporte si se vendió al menos 1 vez
            this.cantidadesVendidas.put(item, cantidad);
            this.recaudadoPorItem.put(item, recaudado);
        }
    }


    private List<Item> getItemsOrdenadosPorVentas() {
        return this.cantidadesVendidas.keySet().stream()
                .sorted((item1, item2) -> this.cantidadesVendidas.get(item2).compareTo(this.cantidadesVendidas.get(item1))) // ordena los ítems de mayor a menor cantidad vendida
                .collect(Collectors.toList());
    }


    public String exportarTextoPlano() {
        String reporte = "Reporte de Más Vendidos:\n";
        
        for (Item item : this.getItemsOrdenadosPorVentas()) {
            int cant = this.cantidadesVendidas.get(item);
            double precioPromedio = this.recaudadoPorItem.get(item) / cant;
            
            reporte += "- " + item.getNombre() + " | Vendidos: " + cant + " | Promedio: $" + precioPromedio + "\n";
        }
        
        return reporte;
    }


    public String exportarCSV() {
        String reporte = "Nombre,Cantidad,Precio Promedio\n";
        
        for (Item item : this.getItemsOrdenadosPorVentas()) {
            int cant = this.cantidadesVendidas.get(item);
            double precioPromedio = this.recaudadoPorItem.get(item) / cant;
            
            reporte += item.getNombre() + "," + cant + "," + precioPromedio + "\n";
        }
        
        return reporte;
    }


    public String exportarHTML() {
        String reporte = "<ul>\n";
        
        for (Item item : this.getItemsOrdenadosPorVentas()) {
            int cant = this.cantidadesVendidas.get(item);
            double precioPromedio = this.recaudadoPorItem.get(item) / cant;
            
            reporte += "<li>" + item.getNombre() + " (Vendidos: " + cant + ")</li>\n";
        }
        
        reporte += "</ul>";
        
        return reporte;
    }
    
    
    
}