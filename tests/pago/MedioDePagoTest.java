package pago;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pedido.Pedido;



public class MedioDePagoTest { //Con este test cubrimos el caso en que, si algún día alguien entra a MedioDePago.java y cambia el orden (por ejemplo, pone la notificación antes de ejecutar la transacción), la barra de JUnit va a marcarle rojo.

	
	
    private MedioDePago medioDePagoSUT;
    private Pedido pedidoMock;

    
    
    @BeforeEach
    public void setUp() {
        pedidoMock = mock(Pedido.class);
        
        medioDePagoSUT = spy(new MedioDePago() { // instanciamos la clase abstracta usando una clase anónima vacía(podemos hacer esto ya que le hicimos override a los 3 mensajes abstractos( o sea que los "implementamos" para que ya no sean abstractos y podamos instanciar la interfaz).
            @Override
            protected void validarDatos(Pedido pedido) {}

            @Override
            protected void reservarFondos(Pedido pedido) {}

            @Override
            protected void ejecutarTransaccion(Pedido pedido) {}
        });
    }



    @Test
    public void test001ProcesarPagoRespetaElEsqueletoDelTemplateMethodDeFormaEstricta() {
        medioDePagoSUT.procesarPago(pedidoMock);
        
        InOrder ordenDeEjecucion = inOrder(medioDePagoSUT);
        
        ordenDeEjecucion.verify(medioDePagoSUT).validarDatos(pedidoMock); // verificamos que los métodos hayan sido llamados en este orden exacto
        ordenDeEjecucion.verify(medioDePagoSUT).reservarFondos(pedidoMock);
        ordenDeEjecucion.verify(medioDePagoSUT).ejecutarTransaccion(pedidoMock);
        ordenDeEjecucion.verify(medioDePagoSUT).notificarResultado(pedidoMock);
    }
    
    
    
}