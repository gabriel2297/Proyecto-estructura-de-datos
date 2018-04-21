package model.facturacion;

/**
 * Clase cola que se encarga de meter facturas a la cola
 * 
 * @author Alonso
 */
public class Cola {
    private static NodoC frente;
    private static NodoC ultimo;
    
   
   /**
    * Metodo encargado de agregar facturas a la cola
    * @param d - recibe el nodo con la informacion del cliente
    */  
    public static void meteFacturas(NodoC d){
      if(frente==null){
         frente=d;
         ultimo=d;
      }else{
         ultimo.setAtras(d);
         ultimo=d;
      }
   }
   
}
