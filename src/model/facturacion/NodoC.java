package model.facturacion;
import model.Cliente;

/**
 * Clase nodo que mantiene los datos del cliente. POJO para la cola
 * 
 * @author Alonso
 */
public class NodoC {
    private Cliente dato;
    private NodoC atras;

    /**
     * Constructor
     * @param dato - recibe el cliente como dato 
     */
    public NodoC(Cliente dato){
        this.dato = dato;
    }

    /**
     * Metodo que devuelve al cliente como objeto
     * @return dato - objeto cliente
     */
    public Cliente getDato() {
        return dato;
    }

    /**
     * Metodo que recibe un cliente y lo agrega al nodo
     * @param dato - el objeto cliente
     */
    public void setDato(Cliente dato) {
        this.dato = dato;
    }

    /**
     * Metodo que devuelve el nodo que esta atras en la cola
     * @return el nodo de atras
     */
    public NodoC getAtras() {
        return atras;
    }

    /**
     * Metodo para asignar el nodo que esta atras del actual
     * @param atras - el nodo que va a ir atras
     */
    public void setAtras(NodoC atras) {
        this.atras = atras;
    }
}
