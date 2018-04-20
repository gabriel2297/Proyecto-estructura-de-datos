/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.venta;

import model.lista.Nodo;

/**
 *
 * @author gabriel
 */
public class Dato {
    private Nodo dato;
    private int cantidad = 0;
    private Dato siguiente;
    
    public Dato(Nodo dato, int cantidad) {
        this.dato = dato;
        this.cantidad = cantidad;
    }

    public Dato(){}
    
    public Nodo getDato() {
        return dato;
    }

    public void setDato(Nodo dato) {
        this.dato = dato;
    }

    public Dato getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Dato siguiente) {
        this.siguiente = siguiente;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
}
