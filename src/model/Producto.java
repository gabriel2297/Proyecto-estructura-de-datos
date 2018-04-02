package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Producto {

    private StringProperty nombre = new SimpleStringProperty();
    private IntegerProperty codigo = new SimpleIntegerProperty();
    private IntegerProperty cantidadBodega = new SimpleIntegerProperty();
    private DoubleProperty precioCompra = new SimpleDoubleProperty();
    private DoubleProperty precioVenta = new SimpleDoubleProperty();

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Producto(String nombre, int codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleIntegerProperty(codigo);

        // Some initial dummy data, just for convenient testing.
        this.cantidadBodega = new SimpleIntegerProperty(10);
        this.precioCompra = new SimpleDoubleProperty(1000.0);
        this.precioVenta = new SimpleDoubleProperty(2000.0);
    }

    public Producto() {
    }

    

    public String getNombre(){
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }
    
    public double getPrecioVenta(){
        return precioVenta.get();
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta.set(precioVenta);
    }

    public DoubleProperty precioVentaProperty() {
        return precioVenta;
    }
    
    public double getPrecioCompra(){
        return precioCompra.get();
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra.set(precioCompra);
    }

    public DoubleProperty precioCompraProperty() {
        return precioCompra;
    }
    
    public int getCantidadBodega(){
        return cantidadBodega.get();
    }

    public void setCantidadBodega(int cantidadBodega) {
        this.cantidadBodega.set(cantidadBodega);
    }

    public IntegerProperty cantidadBodegaProperty() {
        return cantidadBodega;
    }
    
}