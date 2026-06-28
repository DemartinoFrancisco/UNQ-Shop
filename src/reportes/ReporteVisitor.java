package reportes;

import catalogo.Producto;
import catalogo.Paquete;



public interface ReporteVisitor {



    public void visitarProducto(Producto producto);


    public void visitarPaquete(Paquete paquete);
    
    
    
}