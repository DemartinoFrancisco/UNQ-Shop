package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Deposito;
import catalogo.Item;



public class PorDisponibilidadTest {

	
	
    private PorDisponibilidad criterioDisponibilidad;
    private Deposito deposito1;
    private Deposito deposito2;
    private Item itemMock;

    
    
    @BeforeEach
    public void setUp() {
        deposito1 = mock(Deposito.class);
        deposito2 = mock(Deposito.class);
        itemMock = mock(Item.class);
        
        criterioDisponibilidad = new PorDisponibilidad(Arrays.asList(deposito1, deposito2));
    }



    @Test
    public void test001SatisfaceDevuelveTrueSiAlMenosUnDepositoTieneStock() {
        when(deposito1.tieneStock(itemMock)).thenReturn(false);
        when(deposito2.tieneStock(itemMock)).thenReturn(true);
        
        assertTrue(criterioDisponibilidad.satisface(itemMock));
    }


    @Test
    public void test002SatisfaceDevuelveFalseSiNingunDepositoTieneStock() {
        when(deposito1.tieneStock(itemMock)).thenReturn(false);
        when(deposito2.tieneStock(itemMock)).thenReturn(false);
        
        assertFalse(criterioDisponibilidad.satisface(itemMock));
    }
    
    
    
}