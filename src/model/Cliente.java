/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gabriek
 */
public class Cliente {
    private StringProperty nombre = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> fechaCompra;
    private DoubleProperty total = new SimpleDoubleProperty();
    
    public Cliente(){
        this(null,0);
    }
    
    public Cliente(String nombre, int total){
        this.nombre = new SimpleStringProperty(nombre);
        this.fechaCompra = new SimpleObjectProperty<>(LocalDateTime.now());
        this.total = new SimpleDoubleProperty(total);
    }

    public String getNombre(){
        return nombre.get();
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public StringProperty nombreProperty(){
        return nombre;
    }
    
    public LocalDateTime getFechaCompra() {
        return fechaCompra.get();
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra.set(fechaCompra);
    }

    public ObjectProperty<LocalDateTime> fechaCompraProperty() {
        return fechaCompra;
    }
    
    public double getTotal(){
        return total.get();
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public DoubleProperty totalProperty() {
        return total;
    }
    
}
