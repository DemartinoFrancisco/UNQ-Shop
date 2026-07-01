package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class AndTest {

	
	
    private And andCriterio;
    private Criterio criterio1;
    private Criterio criterio2;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        criterio1 = mock(Criterio.class);
        criterio2 = mock(Criterio.class);
        itemMock = mock(Item.class);
        
        andCriterio = new And(Arrays.asList(criterio1, criterio2));
    }



    @Test
    public void test001SatisfaceDevuelveTrueSiTodosLosCriteriosSeCumplen() {
        when(criterio1.satisface(itemMock)).thenReturn(true);
        when(criterio2.satisface(itemMock)).thenReturn(true);
        
        assertTrue(andCriterio.satisface(itemMock));
    }


    @Test
    public void test002SatisfaceDevuelveFalseSiAlMenosUnCriterioNoSeCumple() {
        when(criterio1.satisface(itemMock)).thenReturn(true);
        when(criterio2.satisface(itemMock)).thenReturn(false);
        
        assertFalse(andCriterio.satisface(itemMock));
    }
    
    
    
}