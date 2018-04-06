/**
 * Lista circular doblemente enlazada.
 * Mantiene todos los metodos encargados del CRUD del producto
 */
package model.lista;

import model.Producto;

/**
 *
 * @author gabriel
 */
public class Lista {
    static private Nodo inicio;
    static private Nodo ultimo;
    
    /**
     * Revisar si la lista esta vacia
     * @return true si inicio = null, false si no. 
     */
    public static boolean listaVacia(){
        return inicio == null;
    }
    
    /**
     * Metodo para agregar productos a la lista. 
     * Si la lista esta vacia agrega el producto al inicio y crea un punter hacia el siguiente (el mismo) y el anterior (el mismo).
     * Si el codigo del producto es menor al actual inicio, pone el nuevo producto en inicio y actualiza los punteros.
     * Si el codigo es mayor al ultimo, pone el nuevo producto de ultimo y actualiza punteros.
     * Si no, busca en donde se encuentra en el medio y actualiza el puntero.
     * @param p recibe un producto.
     */
    public static void agregarProducto(Producto p){
        // si la lista esta vacia
        if(listaVacia()){
            inicio = new Nodo(p); 
            ultimo = inicio;
            ultimo.setSiguiente(inicio);
            inicio.setAnterior(ultimo);
        }
        else if(p.getCodigo()<inicio.getDato().getCodigo()){ // si el nuevo dato es menor al dato en inicio
            Nodo aux = new Nodo(p); 
            aux.setSiguiente(inicio);
            inicio = aux;
            ultimo.setSiguiente(inicio);
            inicio.setAnterior(ultimo);
        }
        else if(p.getCodigo()>ultimo.getDato().getCodigo()){ // si el nuevo dato es maoor al ultimo
            Nodo aux = new Nodo(p);
            ultimo.setSiguiente(aux);
            ultimo = ultimo.getSiguiente();
            ultimo.setSiguiente(inicio);
            inicio.setAnterior(ultimo);
        }
        else{ // si el nuevo nodo va en el medio de la lista
            Nodo aux = inicio;
            while(aux.getSiguiente().getDato().getCodigo()<p.getCodigo()){
                aux = aux.getSiguiente();
            }
            Nodo temp = new Nodo(p);
            temp.setSiguiente(aux.getSiguiente());
            temp.setAnterior(aux);
            aux.setSiguiente(temp);
            temp.getSiguiente().setAnterior(temp);
        }
    }
    
    /**
    * Metodo para revisar si un codigo ya existe en la lista. 
    * @param codigo recibe el codigo del producto a revisar
    * @return retorna true si ya existe, false si no.
    */
    public static boolean esDuplicado(int codigo){
        boolean dup = false;
        if(!listaVacia()){
            Nodo aux = inicio;
            // el codigo es igual al del primer dato? Si sí, devuelva true
            if(aux.getDato().getCodigo() == codigo){
               dup = true;
            }
            // revisar cada siguiente nodo y devolver true si í
            else{
                aux = aux.getSiguiente();
                while(aux!=inicio){
                    if(aux.getDato().getCodigo() == codigo){
                        dup = true;
                        break;
                    }
                    else{
                         aux = aux.getSiguiente();
                    }
                }
            }
        return dup;
        }
        // si la lista esta vacia devuelve false
        else{
            return dup;
        }
    }
    
    /**
     * Modificar un nodo.
     * @param codigo recibe el codigo del nodo
     * @param cantidad nueva cantidad
     * @param precioCompra nuevo precio de compra
     * @param precioVenta nuevo precio de venta
     * @param nombre nuevo nombre
     */
    public static void modificar(int codigo, int cantidad, double precioCompra, double precioVenta, String nombre){
        Nodo modifica = existe(codigo);
        if(modifica!=null){
            modifica.getDato().setCantidadBodega(cantidad);
            modifica.getDato().setNombre(nombre);
            modifica.getDato().setPrecioCompra(precioCompra);
            modifica.getDato().setPrecioVenta(precioVenta);
        }
    }
    
    /**
     * Buscar si existe un nodo en la lista con base al codigo
     * @param codigo
     * @return retorna el nodo si se encuentra, null si no. 
     */
    public static Nodo existe(int codigo){
        Nodo existe = null;
        if(!listaVacia()){
            Nodo aux = inicio;
            Nodo aux2 = ultimo;
            // es el primer nodo?
            if(aux.getDato().getCodigo() == codigo){
                existe = aux;
            }
            // es el ultimo?
            else if(aux2.getDato().getCodigo()==codigo){
                existe = aux2;
            }
            // esta en el medio?
            else{
                aux = aux.getSiguiente();
                while(aux!=ultimo){
                    if(aux.getDato().getCodigo() == codigo){
                        existe = aux;
                        break;
                    }
                    else{
                        aux = aux.getSiguiente();
                    }
                }
            }
            return existe;
        }
        else{
            return existe;
        }
    }
    
    /**
     * Eliminar un nodo. 
     * @param eliminar recibe en nodo a eliminar, lo busca, reacomoda la lista y termina.
     */
    public static void eliminar(Nodo eliminar){
        if(ultimo==inicio){
            eliminar.setDato(null);
            inicio = null;
            ultimo = null;
        }
        else{
            if(eliminar == inicio){
                inicio = eliminar.getSiguiente();
                ultimo.setSiguiente(inicio);
                eliminar.setDato(null);
            }
            else if(eliminar == ultimo){
                Nodo temp = inicio;
                while(temp.getSiguiente()!=ultimo){
                    temp = temp.getSiguiente();
                }
                ultimo = temp;
                ultimo.setSiguiente(inicio);
                eliminar.setDato(null);
            }
            else{
                Nodo temp = inicio;
                while(temp.getSiguiente()!=eliminar){
                    temp = temp.getSiguiente();
                }
                temp.setSiguiente(eliminar.getSiguiente());
                eliminar.setDato(null);
            }
        }
    }    
    
    /**
     * Muestra en consola todos los productos en lista.
     * 
     */
//    public void mostrarProductos(){
//        if(!listaVacia()){
//            Nodo aux = inicio;
//            String s = "Productos en inventario: \n";
//            s+="Nombre del producto: "+aux.getDato().getNombre()+
//                    "\nCantidad en bodega: "+aux.getDato().getCantidadBodega()+"\nCodigo: "+aux.getDato().getCodigo()+
//                    "\nPrecio de compra: "+aux.getDato().getPrecioCompra()+"\nPrecio de venta: "+aux.getDato().getPrecioVenta()
//                    +"\n==========================\n";
//            aux = aux.getSiguiente();
//            while(aux!=inicio){
//                s+="Nombre del producto: "+aux.getDato().getNombre()+
//                    "\nCantidad en bodega: "+aux.getDato().getCantidadBodega()+"\nCodigo: "+aux.getDato().getCodigo()+
//                    "\nPrecio de compra: "+aux.getDato().getPrecioCompra()+"\nPrecio de venta: "+aux.getDato().getPrecioVenta()
//                    +"\n==========================\n";
//                aux = aux.getSiguiente();
//            }
//            System.out.println(s);
//        }
//        else{
//            System.out.println("Lista vacia");
//        }
//    }
    
    
}
