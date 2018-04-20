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
    
    // editar algun producto que lleva la persona
    public static void editarVenta(Nodo aux, int codigo){
        if(aux!=null){
            Dato aux2 = p.existe(codigo);
            if(aux2!=null){
                // volver a agregar a la cantidad en bodega los que se estan vendiendo porque ya los resté cuando los agregué,
                // por ejemplo si tengo 2 y reste los dos entonces queda cantidadBodega = 0 pero si quiero solo vender 1
                // y tengo 0 en bodega como hago? Vuelvo a agregarlos al inventario
                int restaurarBodega = aux.getDato().getCantidadBodega()+aux2.getCantidad();
                int nuevaCantidad = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Nueva cantidad:\n"));
                if(nuevaCantidad>restaurarBodega){
                    javax.swing.JOptionPane.showMessageDialog(null,"No hay suficiente producto en inventario. ");
                }
                else{
                    aux.getDato().setCantidadBodega(restaurarBodega-nuevaCantidad);
                    p.editarProductoPila(codigo, nuevaCantidad);
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(null, "Codigo no existente entre las compras del cliente");
            }
        }
        else{
            javax.swing.JOptionPane.showMessageDialog(null, "Codigo no existente. ");
        }
    }
    
    // mostrar los productos en venta hasta el momento
    public void mostrarVenta(){
        p.mostrarPila();
    }
    
    public boolean ventaVacia(){
        return p.pilaVacia();
    }
    
    public void eliminarVenta(){
        p.vaciarPila();
    }
    
    public void mostrarFacturaCliente(){
        p.facturaCliente();
    }
    
//    public void mostrarInformeDeFactura(){
//        p.informeDeFacturas();
//    }
    
}

