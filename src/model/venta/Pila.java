package model.venta;


/**
 * Clase pila para guardar la informacion de la venta
 * 
 * @author Elena
 */
public class Pila {
   static Dato inicio;
   static int tam;
   private static double cantidadTotal = 0.0;

   /**
    * Constructor para inicializar la pila
    */
    public Pila() {
        inicio = null; 
        tam = 0;
    }

    /**
     * Verificar si la pila esta vacia o no
     * @return true si el inicio de pila = null, false si no
     */
    public static boolean pilaVacia() {
        return inicio == null;
    }

    /**
     * Agregar datos a la pila. 
     * @param dato recibe un dato de tipo nodo 
     */
    public static void agregar(Dato dato) {
        if (pilaVacia()) {
            inicio = dato;

        } else {
            dato.setSiguiente(inicio);
            inicio = dato;
        }
        tam++;
    }
    
    /**
     * Metodo para vaciar la pila.
     */
    public static void vaciarPila(){
        inicio = null;
        tam = 0;
    }
    
    /**
     * Verifica si el codigo ingresado ya existe en la pila. 
     * @param codigo recibe el codigo del producto que esta siendo ingresado o se quiere verificar
     * @return retorna el dato si el codigo ya existe. Null si no existe
     */
    public static Dato existe(int codigo){
        Dato existe = null;
        if(!pilaVacia()){
            Dato aux = inicio;
            while(aux!=null){
                if(aux.getDato().getDato().getCodigo() == codigo){
                    existe = aux;
                    break;
                }
                else{
                    aux = aux.getSiguiente();
                }
            }
            return existe;
        }
        else{
            return existe;
        }
    }
    
    /**
     * Metodo para editar la cantidad de un producto en la pila
     * @param codigo recibe el codigo del producto que se quiere editar
     * @param cantidad recibe la nueva cantidad que se quiere usar
     */
    public static void editarProductoPila(int codigo, int cantidad) {
        Dato aux = inicio;
        while (aux != null) {
            if(aux.getDato().getDato().getCodigo() == codigo){
                aux.setCantidad(cantidad);
                break;
            }
            else{
                aux = aux.getSiguiente();
            }
        }
    }
    
    /**
     * Metodo que elimina un dato en especifico de la pila de ventas 
     * @param dato - el dato que se quiere eliminar
     */
    public static void eliminarDato(Dato dato){
         Dato existe = existe(dato.getDato().getDato().getCodigo());
         // si es el unico dato en pila
         if(existe == inicio && inicio.getSiguiente()==null){
             existe = null;
             inicio = null;
             tam = 0;
         }
         //si es el valor de inicio pero no es el unico dato
         else if(existe==inicio){
             inicio = existe.getSiguiente();
             existe = null;
             tam--;
         }
         // busquelo
         else{
             Dato aux = inicio;
             while(aux.getSiguiente()!=existe){
                 aux = aux.getSiguiente();
             }
             aux.setSiguiente(existe.getSiguiente());
             existe = null;
             tam--;
         }
     }  
}
