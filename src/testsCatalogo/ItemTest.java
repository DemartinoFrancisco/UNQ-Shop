package testsCatalogo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogo.Item;
import reportes.ReporteVisitor;



public class ItemTest {

	
	
    private Item item;

    
    
    @BeforeEach
    public void setUp() {
        item = new Item("Auriculares", "Audio HQ", 1000.0, 0.5, 0.0) {
            @Override
            public double getPrecioFinal() {
                return getPrecioBase();
            }
            @Override
            public void accept(ReporteVisitor visitor) {}
        };
    }

    
    
    @Test
    public void test001_agregarYRecuperarAtributoDinamico() {
        item.addAtributo("Color", "Negro");
        item.addAtributo("GarantiaMeses", 12);
        
        assertEquals("Negro", item.getAtributo("Color"));
        assertEquals(12, item.getAtributo("GarantiaMeses"));
    }

    
    @Test
    public void test002_validarBaseExitoso() {
        assertTrue(item.validar(), "El item debería ser válido con todos sus datos correctos.");
    }

    
    @Test
    public void test003_validarFallaPorAtributoDinamicoNulo() {
        item.addAtributo("Alto", null); 
        
        assertFalse(item.validar(), "El item no debe ser válido si tiene atributos nulos.");
    }

    
    @Test
    public void test004_equalsDevuelveTrueParaElMismoObjeto() {
        assertTrue(item.equals(item));
    }

    
    @Test
    public void test005_equalsDevuelveTrueParaDistintosObjetosConMismoNombre() {
        Item otroItem = new Item("Auriculares", "Otra desc", 500.0, 1.0, 0.0) {
            @Override public double getPrecioFinal() { return 0; }
            @Override public void accept(ReporteVisitor visitor) {}
        };
        
        assertTrue(item.equals(otroItem));
    }

    
    @Test
    public void test006_equalsDevuelveFalseParaDistintosNombres() {
        Item otroItem = new Item("Mouse", "Audio HQ", 1000.0, 0.5, 0.0) {
            @Override public double getPrecioFinal() { return 0; }
            @Override public void accept(ReporteVisitor visitor) {}
        };
        
        assertFalse(item.equals(otroItem));
    }
    
    
    
}