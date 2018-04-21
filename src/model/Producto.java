package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase que mantiene toda la informacion de los productos
 *
 * @author Gabriel
 */
public class Producto {

    private StringProperty nombre = new SimpleStringProperty();
    private IntegerProperty codigo = new SimpleIntegerProperty();
    private IntegerProperty cantidadBodega = new SimpleIntegerProperty();
    private DoubleProperty precioCompra = new SimpleDoubleProperty();
    private DoubleProperty precioVenta = new SimpleDoubleProperty();

    /**
     * Constructor con datos para testear
     * 
     * @param nombre - recibe el nombre del producto 
     * @param codigo - recibe el codigo del producto
     */
    public Producto(String nombre, int codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleIntegerProperty(codigo);

        // datos para testear
        this.cantidadBodega = new SimpleIntegerProperty(10);
        this.precioCompra = new SimpleDoubleProperty(1000.0);
        this.precioVenta = new SimpleDoubleProperty(2000.0);
    }

    /**
     * Constructor por defecto
     */
    public Producto() {
    }

    /**
     * Metodo que retorna el nombre que tiene el producto
     * @return nombre - retorna el nombre
     */
    public String getNombre(){
        return nombre.get();
    }

    /**
     * Metodo que asigna el nombre al producto
     * @param nombre - el nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Metodo que retorna el nonbre como un StringProperty
     * @return nombre - el nombre
     */
    public StringProperty nombreProperty() {
        return nombre;
    }

    /**
     * Metodo que retorna el codigo de producto como int
     * @return codigo - el codigo
     */
    public int getCodigo() {
        return codigo.get();
    }

    /**
     * Metodo que recibe como parametro el codigo y lo asigna
     * @param codigo - el codigo a asignar al producto
     */
    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    /**
     * Metodo que devuelve el codigo como un IntegerProperty
     * @return el codigo
     */
    public IntegerProperty codigoProperty() {
        return codigo;
    }
    
    /**
     * Metodo que devuelve el precio de venta 
     * @return precioVenta - el precio de venta
     */
    public double getPrecioVenta(){
        return precioVenta.get();
    }

    /**
     * Metodo para asignar el precio de venta
     * @param precioVenta - el precio de venta
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta.set(precioVenta);
    }

    /**
     * Metodo que retorna el precio de venta como DoubleProperty
     * @return precioVenta - precio de venta como doubleProperty
     */
    public DoubleProperty precioVentaProperty() {
        return precioVenta;
    }
    
    /**
     * Metodo que regresa el precio de compra como double
     * @return precioCompra - precio de compra como double
     */
    public double getPrecioCompra(){
        return precioCompra.get();
    }

    /**
     * Metodo que asigna el precio de compra 
     * @param precioCompra - recibe el precio de compra
     */
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra.set(precioCompra);
    }

    /**
     * Metodo que retorna el precio de compra como doubleProperty
     * @return precioCompra - el precio de compra
     */
    public DoubleProperty precioCompraProperty() {
        return precioCompra;
    }
    
    /**
     * Devuelve la cantidad del producto en bodega
     * @return cantidadBodega - la cantidad
     */
    public int getCantidadBodega(){
        return cantidadBodega.get();
    }

    /**
     * Asigna la cantidad de producto que hay en bodega
     * @param cantidadBodega - recibe la cantidad como int
     */
    public void setCantidadBodega(int cantidadBodega) {
        this.cantidadBodega.set(cantidadBodega);
    }

    /**
     * Devuelve la cantidad en bodega como IntegerProperty
     * @return cantidadBodega - devuelve la cantidad
     */
    public IntegerProperty cantidadBodegaProperty() {
        return cantidadBodega;
    }
    
}