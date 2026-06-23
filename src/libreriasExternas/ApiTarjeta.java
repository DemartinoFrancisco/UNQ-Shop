package libreriasExternas;

import java.time.LocalDate;



public interface ApiTarjeta {
    public boolean validar(int numero, int cvv, LocalDate vencimiento);
    public void preAutorizar(double monto);
    public void transferir(double monto);
    
    
    
}