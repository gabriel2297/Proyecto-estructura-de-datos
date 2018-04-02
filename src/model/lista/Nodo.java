package model.lista;

import model.Producto;

/**
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
     * @param nodo
     */
    public Nodo(Producto dato){
        this.dato = dato;
    }

    public Producto getDato() {
        return dato;
    }

    public void setDato(Producto dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return ""+dato;
    }
    
}
