package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Catalogo;
import catalogo.Item;
import busqueda.Criterio;
import reportes.ReporteVisitor;



public class CatalogoTest {

	
	
    private Catalogo catalogo;
    private Item itemMock1;
    private Item itemMock2;
    private Criterio criterioMock;
    private ReporteVisitor visitorMock;

    
    
    @BeforeEach
    public void setUp() {
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
        criterioMock = mock(Criterio.class);
        visitorMock = mock(ReporteVisitor.class);
        
        catalogo = new Catalogo(Arrays.asList(itemMock1, itemMock2));
    }

    
    
    @Test
    public void test001_buscarDevuelveSoloItemsQueSatisfacenElCriterio() {
        when(criterioMock.satisface(itemMock1)).thenReturn(true);
        when(criterioMock.satisface(itemMock2)).thenReturn(false);
        
        List<Item> resultado = catalogo.buscar(criterioMock);
        
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(itemMock1));
        assertFalse(resultado.contains(itemMock2));
    }

    
    @Test
    public void test002_acceptPasaElVisitorATodosLosItemsDelCatalogo() {
        catalogo.accept(visitorMock);
        
        verify(itemMock1, times(1)).accept(visitorMock);
        verify(itemMock2, times(1)).accept(visitorMock);
    }
    
    
    
}