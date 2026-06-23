package libreriasExternas;



public interface ApiBilleteraVirtual {
    public boolean verificarSaldo(double monto);
    public void bloquearSaldo(double monto);
    public void acreditar(double monto);
    
    
    
}