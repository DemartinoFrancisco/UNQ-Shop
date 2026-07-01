package pago;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import libreriasEInterfacesExternas.ApiTransferencia;
import pedido.Pedido;



public class TransferenciaTest {

	
	
    private Transferencia transferencia;
    private ApiTransferencia apiMock;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        apiMock = mock(ApiTransferencia.class);
        pedidoMock = mock(Pedido.class);
        transferencia = new Transferencia(apiMock, 11223344, "unq.shop.alias");
        
        when(pedidoMock.calcularTotalProductos()).thenReturn(3000.0);
        when(pedidoMock.calcularCostoEnvio()).thenReturn(0.0);
    }



    @Test
    public void test001ProcesarPagoLlamaALosMetodosDeLaApiTransferenciaDeFormaDirecta() {
        transferencia.procesarPago(pedidoMock);
        
        double totalEsperado = 3000.0;
        
        verify(apiMock).validar(11223344, "unq.shop.alias");
        verify(apiMock).transferirInmediato(totalEsperado); // no hay reservar fondos en este medio de pago
    }
    
    
    
}