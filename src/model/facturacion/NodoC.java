/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.facturacion;

import model.Cliente;

/**
 *
 * @author Alonso
 */
public class NodoC {
    private Cliente dato;
    private NodoC atras;

    public NodoC(Cliente dato){
        this.dato = dato;
    }

    public Cliente getDato() {
        return dato;
    }

    public void setDato(Cliente dato) {
        this.dato = dato;
    }

    public NodoC getAtras() {
        return atras;
    }

    public void setAtras(NodoC atras) {
        this.atras = atras;
    }
}
