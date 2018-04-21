package model.venta;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.lista.Nodo;

/**
 * Clase dato que mantiene la informacion de los datos en venta
 * 
 * @author Elena
 */
public class Dato {
    private Nodo dato;
    private IntegerProperty cantidad = new SimpleIntegerProperty();
    private DoubleProperty precioFinal = new SimpleDoubleProperty();
    private Dato siguiente;
    
    
    /**
     * Constructor del dato
     * @param dato - recibe el producto como un nodo
     * @param cantidad - recibe la cantidad del producto que se esta vendiendo
     */
    public Dato(Nodo dato, int cantidad) {
        this.dato = dato;
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    /**
     * Constructor sin nada
     */
    public Dato(){}
    
    /**
     * Regresa el dato
     * @return dato - el producto
     */
    public Nodo getDato() {
        return dato;
    }

    /**
     * Metodo para guardar el dato
     * @param dato - el producto que se esta guardando
     */
    public void setDato(Nodo dato) {
        this.dato = dato;
    }

    /**
     * Devuelve la referencia hacia el siguiente producto
     * @return siguiente
     */
    public Dato getSiguiente() {
        return siguiente;
    }

    /**
     * Asigna a la referencia el dato que sigue
     * @param siguiente - el dato
     */
    public void setSiguiente(Dato siguiente) {
        this.siguiente = siguiente;
    }
    
    /**
     * Devuelve la cantidad de producto que se esta llevando
     * @return cantidad del producto a llevar
     */
    public int getCantidad() {
        return cantidad.get();
    }

    /**
     * Asigna la cantidad de producto a llevar
     * @param cantidad - la cantidad como int
     */
    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    /**
     * Devuelve la cantidad como integerproperty
     * @return la cantidad como integerproperty
     */
    public IntegerProperty cantidadProperty() {
        return cantidad;
    }
    
    /**
     * Multiplica el precio del articulo por la cantidad de este
     * 
     * @return precioFinal - devuelve el precio final como un double basado en el calculo
     */
    public double getPrecioFinal(){
        return dato.getDato().getPrecioVenta()*cantidad.get();
    }
    
    /**
     * Multiplica el precio del articulo por la cantidad de este
     * @return precioFinal - devuelve el precio final como doubleProperty basado en el calculo
     */
    public DoubleProperty precioFinalProperty(){
        return new SimpleDoubleProperty(dato.getDato().getPrecioVenta()*getCantidad());
    }
    
    /**
     * Metodo para asignar el precio final del producto
     * @param precioFinal - recibe el precio final
     */
    public void setPrecioFinalProperty(double precioFinal){
        this.precioFinalProperty().set(precioFinal);
    }
    
}
