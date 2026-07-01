package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class PorPrecioMaximoTest {

	
	
    private PorPrecioMaximo criterioPrecio;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        criterioPrecio = new PorPrecioMaximo(10000.0);
        itemMock = mock(Item.class);
    }



    @Test
    public void test001SatisfaceDevuelveTrueSiElPrecioBaseEsMenorAlMaximo() {
        when(itemMock.getPrecioBase()).thenReturn(8000.0);
        
        assertTrue(criterioPrecio.satisface(itemMock));
    }


    @Test
    public void test002SatisfaceDevuelveTrueSiElPrecioBaseEsIgualAlMaximo() {
        when(itemMock.getPrecioBase()).thenReturn(10000.0);
        
        assertTrue(criterioPrecio.satisface(itemMock));
    }


    @Test
    public void test003SatisfaceDevuelveFalseSiElPrecioBaseEsMayorAlMaximo() {
        when(itemMock.getPrecioBase()).thenReturn(10500.0);
        
        assertFalse(criterioPrecio.satisface(itemMock));
    }
    
    
    
}