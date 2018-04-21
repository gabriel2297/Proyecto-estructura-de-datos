package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Metodo POJO del empleado
 * 
 * @author Alonso
 */
public class Empleado {
    private StringProperty nombre = new SimpleStringProperty();
    private IntegerProperty codigo = new SimpleIntegerProperty();
    private DoubleProperty salario = new SimpleDoubleProperty();
    private StringProperty puesto = new SimpleStringProperty();
    private StringProperty contratador = new SimpleStringProperty();

    /**
     * Constructor con datos para testear
     * 
     * @param nombre - recibe el nombre del empleado 
     * @param codigo - recibe el codigo del empleado
     */
    public Empleado(String nombre, int codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleIntegerProperty(codigo);

        // datos para testear
        this.salario = new SimpleDoubleProperty(300000.0);
        this.puesto = new SimpleStringProperty("Miscelaneo");
        this.contratador = new SimpleStringProperty("Alonso Araya");
    }

    /**
     * Constructor por defecto
     */
    public Empleado() {
    }

    /**
     * Metodo que retorna el nombre que tiene el empleado
     * @return nombre - retorna el nombre
     */
    public String getNombre(){
        return nombre.get();
    }

    /**
     * Metodo que asigna el nombre al empleado
     * @param nombre - el nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Metodo que retorna el nombre como un StringProperty
     * @return nombre - el nombre
     */
    public StringProperty nombreProperty() {
        return nombre;
    }

    /**
     * Metodo que retorna el codigo de empleado como int
     * @return codigo - el codigo
     */
    public int getCodigo() {
        return codigo.get();
    }

    /**
     * Metodo que recibe como parametro el codigo y lo asigna
     * @param codigo - el codigo a asignar al empleado
     */
    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    /**
     * Metodo que devuelve el codigo como un integerroperty
     * @return el codigo
     */
    public IntegerProperty codigoProperty() {
        return codigo;
    }
    
    /**
     * Metodo que devuelve el salario
     * @return salario - el salario
     */
    public double getSalario(){
        return salario.get();
    }

    /**
     * Metodo para asignar el salario
     * @param salario - el salario
     */
    public void setSalario(double salario) {
        this.salario.set(salario);
    }

    /**
     * Metodo que retorna el salario como DoubleProperty
     * @return salario - salario como doubleProperty
     */
    public DoubleProperty salarioProperty() {
        return salario;
    }
    
   /**
     * Metodo que retorna el puesto
     * @return puesto - retorna el puesto
     */
    public String getPuesto(){
        return puesto.get();
    }

    /**
     * Metodo que asigna el puesto al empleado
     * @param puesto - el puesto a asignar
     */
    public void setPuesto(String puesto) {
        this.puesto.set(puesto);
    }

    /**
     * Metodo que retorna el puesto como un StringProperty
     * @return puesto - el puesto
     */
    public StringProperty puestoProperty() {
        return puesto;
    }
    
    /**
     * Metodo que retorna el contratador
     * @return contratador - retorna el contratador
     */
    public String getContratador(){
        return contratador.get();
    }

    /**
     * Metodo que asigna el contratador al empleado
     * @param contratador - el contratador a asignar
     */
    public void setContratador(String contratador) {
        this.contratador.set(contratador);
    }

    /**
     * Metodo que retorna el contratador como un StringProperty
     * @return contratador - el contratador
     */
    public StringProperty contratdorProperty() {
        return contratador;
    }
    
}
