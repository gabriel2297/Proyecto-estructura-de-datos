/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.facturacion;

import javax.swing.JOptionPane;

/**
 *
 * @author gabriek
 */
public class Cola {
    private static NodoC frente;
    private static NodoC ultimo;
    
    //Cola Informe De Facturas
     
    public static void meteFacturas(NodoC d){
      if(frente==null){
         frente=d;
         ultimo=d;
      }else{
         ultimo.setAtras(d);
         ultimo=d;
      }
   }
   
}
