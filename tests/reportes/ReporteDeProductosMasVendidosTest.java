package reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.Entregado;
import pedido.Estado;
import catalogo.Producto;
import catalogo.Paquete;



public class ReporteDeProductosMasVendidosTest {

	
	
    private ReporteDeProductosMasVendidos reporte;
    private Producto productoMock;
    private Paquete paqueteMock;
    private Pedido pedidoValido;
    private Pedido pedidoFueraDeFecha;
    private Pedido pedidoCancelado;
    private Entregado estadoEntregadoMock;
    private Estado estadoCanceladoMock;

    
    
    @BeforeEach
    public void setUp() {
        // mocks de los items del catalogo
        productoMock = mock(Producto.class);
        when(productoMock.getNombre()).thenReturn("Auriculares");
        when(productoMock.getPrecioFinal()).thenReturn(8000.0);

        paqueteMock = mock(Paquete.class);
        when(paqueteMock.getNombre()).thenReturn("Pack Gamer");
        when(paqueteMock.getPrecioFinal()).thenReturn(15000.0);

        // mocks de estados
        estadoEntregadoMock = mock(Entregado.class);
        // simulo un estado generico distinto a entregado (por ej. cancelado o borrador)
        estadoCanceladoMock = mock(Estado.class); 

        // pedido 1: valido (entregado y dentro de la fecha)
        pedidoValido = mock(Pedido.class);
        when(pedidoValido.getFecha()).thenReturn(LocalDate.of(2026, 6, 15));
        when(pedidoValido.getEstado()).thenReturn(estadoEntregadoMock);
        when(pedidoValido.getItems()).thenReturn(Arrays.asList(productoMock, productoMock, paqueteMock));

        // pedido 2: fuera de fecha (entregado pero en una fecha fuera del rango que cubre este reporte)
        pedidoFueraDeFecha = mock(Pedido.class);
        when(pedidoFueraDeFecha.getFecha()).thenReturn(LocalDate.of(2026, 8, 10));
        when(pedidoFueraDeFecha.getEstado()).thenReturn(estadoEntregadoMock);
        when(pedidoFueraDeFecha.getItems()).thenReturn(Arrays.asList(productoMock));

        // pedido 3: cancelado (dentro de fecha pero no entregado)
        pedidoCancelado = mock(Pedido.class);
        when(pedidoCancelado.getFecha()).thenReturn(LocalDate.of(2026, 6, 20));
        when(pedidoCancelado.getEstado()).thenReturn(estadoCanceladoMock);
        when(pedidoCancelado.getItems()).thenReturn(Arrays.asList(paqueteMock));

        // rango de fechas para el reporte: todo el mes de Junio 2026
        LocalDate inicio = LocalDate.of(2026, 6, 1);
        LocalDate fin = LocalDate.of(2026, 6, 30);
        
        List<Pedido> pedidosHistoricos = Arrays.asList(pedidoValido, pedidoFueraDeFecha, pedidoCancelado);

        reporte = new ReporteDeProductosMasVendidos(pedidosHistoricos, inicio, fin);
    }



    @Test
    public void test001VisitarProductoGeneraEstadisticasExcluyendoPedidosInvalidos() {
        reporte.visitarProducto(productoMock);
        String resultado = reporte.exportarTextoPlano();
        
        // Assert
        // debe contar solo los 2 auriculares del pedido válido (excluye el pedidoFueraDeFecha y el pedidoCancelado)
        // promedio: (8000 + 8000) / 2 = 8000.0
        assertTrue(resultado.contains("Auriculares | Vendidos: 2 | Promedio: $8000.0"));
    }


    @Test
    public void test002VisitarPaqueteYExportarCSVGeneraFormatoCorrecto() {
        reporte.visitarPaquete(paqueteMock);
        String resultado = reporte.exportarCSV();
        
        // debe contar 1 Pack gamer del pedido valido (excluye el pedidoCancelado y el pedido fuera de fecha)
        String esperado = "Nombre,Cantidad,Precio Promedio\nPack Gamer,1,15000.0\n";
        assertEquals(esperado, resultado);
    }


    @Test
    public void test003VisitarMultiplesItemsExportaHTMLOrdenadoPorVentas() {
        reporte.visitarProducto(productoMock);
        reporte.visitarPaquete(paqueteMock);
        
        String resultado = reporte.exportarHTML();
        
        // El reporte HTML debe tener primero los Auriculares (vendidos: 2) y luego el Pack (vendidos: 1)
        String esperado = "<ul>\n" +
                          "<li>Auriculares (Vendidos: 2)</li>\n" +
                          "<li>Pack Gamer (Vendidos: 1)</li>\n" +
                          "</ul>";
        assertEquals(esperado, resultado);
    }


    @Test
    public void test004ExportarItemNoVendidoNoLoIncluyeEnElReporte() {
        Producto productoNoVendido = mock(Producto.class);
        when(productoNoVendido.getNombre()).thenReturn("Teclado");
        
        reporte.visitarProducto(productoNoVendido);
        String resultado = reporte.exportarTextoPlano();
        
        // Como el Teclado no se vendió en ningún pedido válido, no debe aparecer en el reporte
        assertEquals("Reporte de Más Vendidos:\n", resultado);
    }

    
    
}