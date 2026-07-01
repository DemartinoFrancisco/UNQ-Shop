package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reportes.ReporteVisitor;



public class ProductoTest {

	
	
    private Producto producto;
    private ReporteVisitor visitorMock;

    
    
    @BeforeEach
    public void setUp() {
        producto = new Producto("Teclado", "Mecánico", 1000.0, 1.2, 0.20, 12345, "Electrónica", "Logitech");
        visitorMock = mock(ReporteVisitor.class);
    }

    
    @Test
    public void test001_getPrecioFinalAplicaDescuentoCorrectamente() {
        assertEquals(800.0, producto.getPrecioFinal(), 0.01); // el tercer parametro se llama Delta (o margen de error)
    }

    
    @Test
    public void test002_validarProductoExitoso() {
        assertTrue(producto.validar());
    }

    
    @Test
    public void test003_validarFallaPorSKUInvalido() {
        Producto productoMalo = new Producto("Teclado", "Mecánico", 1000.0, 1.2, 0.20, null, "Electrónica", "Logitech");
        assertFalse(productoMalo.validar(), "Debería fallar porque el SKU es null");
        
        Producto productoMalo2 = new Producto("Teclado", "Mecánico", 1000.0, 1.2, 0.20, 0, "Electrónica", "Logitech");
        assertFalse(productoMalo2.validar(), "Debería fallar porque el SKU no es mayor a 0");
    }

    
    @Test
    public void test004_acceptLlamaAlMetodoVisitarProductoDelVisitor() {
        producto.accept(visitorMock);
        verify(visitorMock, times(1)).visitarProducto(producto);
    }
    
    
    @Test
    public void test005_gettersRetornanValoresCorrectos() {
        assertEquals(12345, producto.getSKU());
        assertEquals("Electrónica", producto.getCategoria());
    }
    
    
    
}