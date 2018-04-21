/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.venta;

import model.lista.Nodo;

/**
 *
 * @author gabriek
 */
public class Venta {
    static Pila p=new Pila();
    private Dato inicio;
    //Factura f=new Factura();
    
    // realizar nueva venta
    
    public static boolean haySuficientes(Dato aux){
        return aux.getCantidad()<=aux.getDato().getDato().getCantidadBodega();
    }
    
    public static void nuevaVenta(Dato aux){
        aux.getDato().getDato().setCantidadBodega(aux.getDato().getDato().getCantidadBodega()-aux.getCantidad());
        Pila.agregar(aux);
    }
    
    public static void restaurarInventario(Nodo aux, int codigo){
        Dato aux2 = p.existe(codigo);
        int restaurarBodega = aux.getDato().getCantidadBodega()+aux2.getCantidad();
        aux.getDato().setCantidadBodega(restaurarBodega);
    }
    
    // editar algun producto que lleva la persona
    public static void editarVenta(int codigo, int nuevaCantidad){
        Dato aux = p.existe(codigo);
        aux.getDato().getDato().setCantidadBodega(aux.getDato().getDato().getCantidadBodega()-aux.getCantidad());
        p.editarProductoPila(codigo, nuevaCantidad);
    }
    
    // mostrar los productos en venta hasta el momento
    public void mostrarVenta(){
        p.mostrarPila();
    }
    
    public boolean ventaVacia(){
        return p.pilaVacia();
    }
    
    public static void eliminarVenta(){
        p.vaciarPila();
    }
    
    public static void eliminarDato(Dato dato){
        p.eliminarDato(dato);
    }
    
    public void mostrarFacturaCliente(){
        p.facturaCliente();
    }
    
//    public void mostrarInformeDeFactura(){
//        p.informeDeFacturas();
//    }
    
}

