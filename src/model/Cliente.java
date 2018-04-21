package model;

import java.time.LocalDateTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase cliente que mantiene los objetos de factura por cliente
 * 
 * @author Alonso
 */
public class Cliente {
    private StringProperty nombre = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> fechaCompra;
    private DoubleProperty total = new SimpleDoubleProperty();
    
    /**
     * Constructor por defecto
     */
    public Cliente(){
        this(null,0);
    }
    
    
    
    /**
     * Constructor que inicializa los valores de nombre y total
     * @param nombre - recibe el nombre del cliente
     * @param total - recibe el total de compra
     */
    public Cliente(String nombre, int total){
        this.nombre = new SimpleStringProperty(nombre);
        this.fechaCompra = new SimpleObjectProperty<>(LocalDateTime.now());
        this.total = new SimpleDoubleProperty(total);
    }

    /**
     * Metodo que retorna el nombre del cliente
     * @return nombre - nombre del cliente
     */
    public String getNombre(){
        return nombre.get();
    }
    
    /**
     * Metodo para agregar el nombre del cliente a la factura
     * @param nombre recibe el nombre del cliente
     */
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    /**
     * Metodo que retorna el nombre de la persona como un StringProperty
     * @return nombre - retorna el nombre del cliente
     */
    public StringProperty nombreProperty(){
        return nombre;
    }
    
    /**
     * Metodo para obtener la fecha de compra en formato de fecha
     * @return fechaCompra - fecha de compra
     */
    public LocalDateTime getFechaCompra() {
        return fechaCompra.get();
    }
    
    /**
     * Metodo que pone la fecha de la compra
     * @param fechaCompra - toma una fecha y la settea
     */
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra.set(fechaCompra);
    }

    /**
     * Metodo para obtener la fecha de compra como un objeto de tipo fecha
     * @return fechaCompra - retorna la fecha
     */
    public ObjectProperty<LocalDateTime> fechaCompraProperty() {
        return fechaCompra;
    }
    
    /**
     * Metodo que regresa el total de la compra del cliente
     * @return total - el total de compra
     */
    public double getTotal(){
        return total.get();
    }

    /**
     * Metodo que pone el total de compra del cliente
     * @param total - recibe el total de la compra
     */
    public void setTotal(double total) {
        this.total.set(total);
    }

    /**
     * Metodo que retorna el total de la compra como un DoubleProperty
     * @return total - el total como doubleProperty
     */
    public DoubleProperty totalProperty() {
        return total;
    }
    
}
