package pago;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import libreriasEInterfacesExternas.ApiTarjeta;
import pedido.Pedido;



public class TarjetaTest {

	
	
    private Tarjeta tarjeta;
    private ApiTarjeta apiMock;
    private Pedido pedidoMock;
    private LocalDate vencimiento;

    
    
    @BeforeEach
    public void setUp() {
        apiMock = mock(ApiTarjeta.class);
        pedidoMock = mock(Pedido.class);
        vencimiento = LocalDate.of(2028, 12, 31);
        tarjeta = new Tarjeta(apiMock, 12345678, 123, vencimiento);
        
        when(pedidoMock.calcularTotalProductos()).thenReturn(2000.0);
        when(pedidoMock.calcularCostoEnvio()).thenReturn(500.0);
    }



    @Test
    public void test001ProcesarPagoLlamaALosMetodosDeLaApiConDatosYTotalCorrectos() {
        tarjeta.procesarPago(pedidoMock);
        
        double totalEsperado = 2500.0;
        
        verify(apiMock).validar(12345678, 123, vencimiento);
        verify(apiMock).preAutorizar(totalEsperado);
        verify(apiMock).transferir(totalEsperado);
    }
    
    
    
}