/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.venta;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Producto;
import model.lista.Nodo;

/**
 *
 * @author gabriel
 */
public class Dato {
    private Nodo dato;
    private IntegerProperty cantidad = new SimpleIntegerProperty();
    private DoubleProperty precioFinal = new SimpleDoubleProperty();
    private Dato siguiente;
    
    
    public Dato(Nodo dato, int cantidad) {
        this.dato = dato;
        this.cantidad = new SimpleIntegerProperty(cantidad);
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
    
    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }
    
    public double getPrecioFinal(){
        return dato.getDato().getPrecioVenta()*cantidad.get();
    }
    
    public DoubleProperty precioFinalProperty(){
        return new SimpleDoubleProperty(dato.getDato().getPrecioVenta()*cantidad.get());
    }
    
    
    
}
