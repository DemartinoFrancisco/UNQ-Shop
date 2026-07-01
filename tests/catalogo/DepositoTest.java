package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.OperacionInvalidaException;



public class DepositoTest {

	
	
    private Deposito deposito;
    private Item itemMock1;
    private Item itemMock2;

    
    
    @BeforeEach
    public void setUp() {
        deposito = new Deposito();
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
    }

    
    
    @Test
    public void test001_registrarIngresoYTieneStock() {
        assertFalse(deposito.tieneStock(itemMock1), "No debería tener stock inicialmente");
        
        deposito.registrarIngresoDeStock(itemMock1, 5);
        
        assertTrue(deposito.tieneStock(itemMock1));
    }

    
    @Test
    public void test002_agregarStockSumaCorrectamenteDesdeLista() {
        List<Item> itemsDevueltos = Arrays.asList(itemMock1, itemMock1, itemMock2);
        
        deposito.agregarStock(itemsDevueltos);
        
        assertTrue(deposito.tieneStock(itemMock1));
        assertTrue(deposito.tieneStock(itemMock2));
    }

    
    @Test
    public void test003_quitarStockDescuentaCorrectamente() {
        deposito.registrarIngresoDeStock(itemMock1, 2); 
        
        List<Item> itemsAQuitar = Arrays.asList(itemMock1, itemMock1);
        
        assertTrue(deposito.tieneStockDeItems(itemsAQuitar), "Debería haber stock suficiente para lo que pide la lista itemsAQuitar");
        
        deposito.quitarStock(itemsAQuitar); 
        
        assertFalse(deposito.tieneStock(itemMock1), "El stock debería haber quedado en 0");
    }

    
    @Test
    public void test004_quitarStockLanzaExcepcionSiNoHayStock() {
        deposito.registrarIngresoDeStock(itemMock1, 1);
        
        List<Item> itemsAQuitar = Arrays.asList(itemMock1, itemMock1);
        
        assertThrows(OperacionInvalidaException.class, () -> {
            deposito.quitarStock(itemsAQuitar);
        }, "Debería lanzar OperacionInvalidaException por falta de stock");
    }

    
    @Test
    public void test005_tieneStockDeItemsRetornaFalseSiFaltaStockDeAlMenosUnItem() {
        deposito.registrarIngresoDeStock(itemMock1, 5);
        deposito.registrarIngresoDeStock(itemMock2, 0); 
        
        List<Item> itemsRequeridos = Arrays.asList(itemMock1, itemMock2);
        
        assertFalse(deposito.tieneStockDeItems(itemsRequeridos));
    }
    
    
    
}