package libreriasExternas;



public interface ApiTransferencia {
    public boolean validar(int cbu, String alias);
    public void transferirInmediato(double monto);
    
    
    
}