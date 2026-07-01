package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class PorNombreTest {

	
	
    private PorNombre criterioNombre;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        criterioNombre = new PorNombre("Cable");
        itemMock = mock(Item.class);
    }



    @Test
    public void test001SatisfaceDevuelveTrueSiElNombreContieneElTextoSinImportarMayusculas() {
        when(itemMock.getNombre()).thenReturn("CABLE USB-C");
        
        assertTrue(criterioNombre.satisface(itemMock));
    }


    @Test
    public void test002SatisfaceDevuelveFalseSiElNombreNoContieneElTexto() {
        when(itemMock.getNombre()).thenReturn("Auriculares Bluetooth");
        
        assertFalse(criterioNombre.satisface(itemMock));
    }
    
    
    
}