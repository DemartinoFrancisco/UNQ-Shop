package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;
import catalogo.Producto;



public class PorCategoriaTest {

	
	
    private PorCategoria criterioCategoria;
    private Producto productoMock;
    private Item paqueteMock;

    
    
    @BeforeEach
    public void setUp() {
        criterioCategoria = new PorCategoria("Electrónica");
        productoMock = mock(Producto.class);
        paqueteMock = mock(Item.class); // Representa un item generico(que puede ser un paquete y no un producto)
    }



    @Test
    public void test001SatisfaceDevuelveTrueSiEsProductoValidoYCoincideCategoria() {
        when(productoMock.validar()).thenReturn(true);
        when(productoMock.getCategoria()).thenReturn("electrónica");
        
        assertTrue(criterioCategoria.satisface(productoMock));
    }


    @Test
    public void test002SatisfaceDevuelveFalseSiEsProductoValidoPeroNoCoincideCategoria() {
        when(productoMock.validar()).thenReturn(true);
        when(productoMock.getCategoria()).thenReturn("Indumentaria");
        
        assertFalse(criterioCategoria.satisface(productoMock));
    }


    @Test
    public void test003SatisfaceDevuelveFalseSiEsProductoPeroNoEsValido() {
        when(productoMock.validar()).thenReturn(false);
        when(productoMock.getCategoria()).thenReturn("Electrónica");
        
        assertFalse(criterioCategoria.satisface(productoMock));
    }


    @Test
    public void test004SatisfaceDevuelveFalseSiElItemNoEsUnProducto() {
        // Al pasarle un item generico podria ser un paquete, asi que debe dar false directo, ya que los paquetes no tienen categoria
        assertFalse(criterioCategoria.satisface(paqueteMock));
    }
    
    
    
}