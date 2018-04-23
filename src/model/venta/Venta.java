package model.venta;

import model.lista.Nodo;

/**
 * Clase que mantiene todos los metodos necesarios para la venta de productos
 * 
 * @author Elena
 */
public class Venta {
    
    /**
     * Metodo que se encarga de revisar si hay suficientes productos en stock como para realizar una venta
     * @param aux - el dato que contiene la cantidad deseada
     * @return true si la cantidad en stock es mayor o igual a la cantidad que se lleva
     */
    
    public static boolean haySuficientes(Dato aux){
        return aux.getCantidad()<=aux.getDato().getDato().getCantidadBodega();
    }
    
    /**
     * Metodo para ingresar un nueo dato a la pila de ventas
     * @param aux - el dato que contiene el producto con la cantidad y demas atributos a llevar.
     */
    public static void nuevaVenta(Dato aux){
        aux.getDato().getDato().setCantidadBodega(aux.getDato().getDato().getCantidadBodega()-aux.getCantidad());
        Pila.agregar(aux);
    }
    
    /**
     * Metodo que restaura el inventario al editar un producto en venta o eliminar los productos
     * @param aux - el dato que se esta eliminando o editando
     * @param codigo - el codigo de este dato
     */
    public static void restaurarInventario(Nodo aux, int codigo){
        Dato aux2 = Pila.existe(codigo);
        int restaurarBodega = aux.getDato().getCantidadBodega()+aux2.getCantidad();
        aux.getDato().setCantidadBodega(restaurarBodega);
    }
    
    /**
     * Metodo que edita una venta en especifico para cambiar la cantidad del producto a llevar
     * @param codigo - el codigo del producto que se quiere cambiar
     * @param nuevaCantidad - la nueva cantidad a llevar
     */
    public static void editarVenta(int codigo, int nuevaCantidad){
        Dato aux = Pila.existe(codigo);
        aux.getDato().getDato().setCantidadBodega(aux.getDato().getDato().getCantidadBodega()-aux.getCantidad());
        Pila.editarProductoPila(codigo, nuevaCantidad);
    }
    
    /**
     * Metodo para eliminar toda la venta. Vacia la pila
     */
    public static void eliminarVenta(){
        Pila.vaciarPila();
    }
    
    /**
     * Metodo para eliminar un dato en especifico de la pila.
     * @param dato - recibe el dato que se quiere eliminar (producto)
     */
    public static void eliminarDato(Dato dato){
        Pila.eliminarDato(dato);
    }
    
}

