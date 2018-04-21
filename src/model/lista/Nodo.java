package model.lista;

import model.Producto;

/**
 * Clase nodo que junta el producto con referencias hacia el producto anterior y siguiente.
 * 
 * @author gabriel
 */
public class Nodo {
    private Producto dato;
    private Nodo siguiente;
    private Nodo anterior;
    
    /**
     * Constructor sin informacion
     */
    public Nodo(){}
    
    /**
     * Constructor con informacion
     * @param dato - recibe el producto a buscarse
     */
    public Nodo(Producto dato){
        this.dato = dato;
    }

    /**
     * Getter que devuelve el producto guardado en el nodo
     * @return dato - producto
     */
    public Producto getDato() {
        return dato;
    }

    /**
     * Metodo que recibe un producto y lo asigna al dato del nodo.
     * @param dato - producto a ser guardado
     */
    public void setDato(Producto dato) {
        this.dato = dato;
    }

    /**
     * Metodo que devuelve la referencia hacia el siguiente nodo
     * @return siguiente - el siguiente nodo
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Metodo que asigna el puntero hacia el siguiente nodo
     * @param siguiente - el nodo que sigue.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Metodo que devuelve la referencia hacia el anterior nodo
     * @return anterior - el anterior nodo
     */
    public Nodo getAnterior() {
        return anterior;
    }

    /**
     * Metodo que asigna el puntero hacia el anterior nodo
     * @param anterior - el nodo que sigue.
     */
    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}
