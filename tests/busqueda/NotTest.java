package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;



public class NotTest {

	
	
    private Not notCriterio;
    private Criterio criterioEnvuelto;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        criterioEnvuelto = mock(Criterio.class);
        itemMock = mock(Item.class);
        
        notCriterio = new Not(criterioEnvuelto);
    }



    @Test
    public void test001SatisfaceDevuelveTrueSiElCriterioEnvueltoEsFalse() {
        when(criterioEnvuelto.satisface(itemMock)).thenReturn(false);
        
        assertTrue(notCriterio.satisface(itemMock));
    }


    @Test
    public void test002SatisfaceDevuelveFalseSiElCriterioEnvueltoEsTrue() {
        when(criterioEnvuelto.satisface(itemMock)).thenReturn(true);
        
        assertFalse(notCriterio.satisface(itemMock));
    }
    
    
    
}