package pago;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import libreriasEInterfacesExternas.ApiBilleteraVirtual;
import pedido.Pedido;



public class BilleteraVirtualTest {

	
	
    private BilleteraVirtual billetera;
    private ApiBilleteraVirtual apiMock;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        apiMock = mock(ApiBilleteraVirtual.class);
        pedidoMock = mock(Pedido.class);
        billetera = new BilleteraVirtual(apiMock);
        
        when(pedidoMock.calcularTotalProductos()).thenReturn(1000.0);
        when(pedidoMock.calcularCostoEnvio()).thenReturn(200.0);
    }



    @Test
    public void test001ProcesarPagoLlamaALosMetodosDeLaApiConElTotalCorrecto() {
    	
        billetera.procesarPago(pedidoMock);
        
        double totalEsperado = 1200.0;
        
        // Verificamos que la api haya recibido los mensajes correctamente
        verify(apiMock).verificarSaldo(totalEsperado);
        verify(apiMock).bloquearSaldo(totalEsperado);
        verify(apiMock).acreditar(totalEsperado);
    }
    
    
    
}