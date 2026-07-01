package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reportes.ReporteVisitor;



public class PaqueteTest {

	
	
    private Paquete paquete;
    private Item itemMock1;
    private Item itemMock2;
    private ReporteVisitor visitorMock;

    
    
    @BeforeEach
    public void setUp() {
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
        
        List<Item> itemsDelPaquete = Arrays.asList(itemMock1, itemMock2);
        
        paquete = new Paquete("Kit Gamer", "Mouse y Teclado", 100.0, 2.0, 0.10, itemsDelPaquete);
        visitorMock = mock(ReporteVisitor.class);
    }

    
    
    @Test
    public void test001_getPrecioFinalSumaItemsYAplicaDescuento() {
        when(itemMock1.getPrecioFinal()).thenReturn(500.0);
        when(itemMock2.getPrecioFinal()).thenReturn(1500.0);
        
        assertEquals(1800.0, paquete.getPrecioFinal(), 0.01);  // el tercer parametro se llama Delta (o margen de error)
    }

    
    @Test
    public void test002_validarPaqueteExitosoCuandoItemsSonValidosYBaseValida() {
        when(itemMock1.validar()).thenReturn(true);
        when(itemMock2.validar()).thenReturn(true);
        
        assertTrue(paquete.validar());
    }

    
    @Test
    public void test003_validarPaqueteFallaSiAlMenosUnItemEsInvalido() {
        when(itemMock1.validar()).thenReturn(true);
        when(itemMock2.validar()).thenReturn(false); 
        
        assertFalse(paquete.validar());
    }

    
    @Test
    public void test004_acceptLlamaAlMetodoVisitarPaqueteDelVisitor() {
        paquete.accept(visitorMock);
        verify(visitorMock, times(1)).visitarPaquete(paquete);
    }
    
    
    @Test
    public void test005_getItemsDevuelveLaListaCorrecta() {
        List<Item> recuperados = paquete.getItems();
        assertTrue(recuperados.contains(itemMock1));
        assertTrue(recuperados.contains(itemMock2));
    }
    
    
}