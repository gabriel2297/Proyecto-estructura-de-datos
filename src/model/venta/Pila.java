/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.venta;

import javax.swing.JOptionPane;
import model.lista.Nodo;

/**
 *
 * @author gabriek
 */
public class Pila {
   static Dato inicio;
   static int tam;
   private static double cantidadTotal = 0.0;

   /**
    * Constructor para inicializar la pila
    */
    public Pila() {
        inicio = null; 
        tam = 0;
    }

    /**
     * Verificar si la pila esta vacia o no
     * @return true si el inicio de pila = null, false si no
     */
    public static boolean pilaVacia() {
        return inicio == null;
    }

    /**
     * Agregar datos a la pila. 
     * @param dato recibe un dato de tipo nodo 
     * @param cantidad recibe la cantidad del producto
     */
    public static void agregar(Dato dato) {
        if (pilaVacia()) {
            inicio = dato;

        } else {
            dato.setSiguiente(inicio);
            inicio = dato;
        }
        tam++;
    }
    
    /**
     * Muestra lo que esta almacenado en la pila mediante un JOptionPane.
     */
    public static void mostrarPila(){
        if(!pilaVacia()){
            String s = "Venta: \n";
            Dato aux = inicio;
            while (aux != null) {
                s+="\nNombre del Producto: "+aux.getDato().getDato().getNombre()+
                        "\nCantidad: "+aux.getCantidad()+
                        "\nCodigo: "+aux.getDato().getDato().getCodigo()+
                        "\nPrecio total con iva: "+((aux.getCantidad()*aux.getDato().getDato().getPrecioVenta())+
                        aux.getCantidad()*(aux.getDato().getDato().getPrecioVenta()*0.13))+
                        "\n============================\n";
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, s);
        }
        else{
            JOptionPane.showMessageDialog(null,"No se ha facturado nada");
        }
    }
    
    
    /**
     * Extraer los datos de la pila de manera recursiva y sumarlos a la cantidad total para obtener el total de venta. 
     * @param dato recibe un dato de tipo Dato
     * @return retorna el total de venta
     */
    public static double extraer(Dato dato) {
        if (dato.getSiguiente() == null) {
            return cantidadTotal+=dato.getCantidad()*dato.getDato().getDato().getPrecioVenta();
        }
        else{
            extraer(dato.getSiguiente());
            cantidadTotal+=dato.getCantidad()*dato.getDato().getDato().getPrecioVenta();
            return cantidadTotal;
        }
    }
    
    /**
     * Metodo para vaciar la pila.
     */
    public static void vaciarPila(){
        inicio = null;
        tam = 0;
    }
    
    /**
     * Verifica si el codigo ingresado ya existe en la pila. 
     * @param codigo recibe el codigo del producto que esta siendo ingresado o se quiere verificar
     * @return retorna true si el codigo ya existe o false si no. 
     */
    public static Dato existe(int codigo){
        Dato existe = null;
        if(!pilaVacia()){
            Dato aux = inicio;
            while(aux!=null){
                if(aux.getDato().getDato().getCodigo() == codigo){
                    existe = aux;
                    break;
                }
                else{
                    aux = aux.getSiguiente();
                }
            }
            return existe;
        }
        else{
            System.out.println("No se ha facturado nada");
            return existe;
        }
    }
    
    /**
     * Metodo para editar la cantidad de un producto en la pila
     * @param codigo recibe el codigo del producto que se quiere editar
     * @param cantidad recibe la nueva cantidad que se quiere usar
     */
    public static void editarProductoPila(int codigo, int cantidad) {
        Dato aux = inicio;
        while (aux != null) {
            if(aux.getDato().getDato().getCodigo() == codigo){
                aux.setCantidad(cantidad);
                break;
            }
            else{
                aux = aux.getSiguiente();
            }
        }
    }
     
    /**
     * Facturar la venta total del cliente, recibe el precio final del metodo extraer y pone la fila en 0 al terminar
     */
     public static void facturaCliente(){
         if(!pilaVacia()){
            double precioFinal = 0;
            String nomCliente=javax.swing.JOptionPane.showInputDialog("Nombre del Cliente");
            String s = "\nFactura de: "+nomCliente+ "\n";
            precioFinal = extraer(inicio);
            // impuesto en CR es de 13%. Calcular impuesto, luego sumarselo al precio final e imprimir el resultado 
            final double IMPUESTO = 0.13;
            double total = precioFinal + (precioFinal*IMPUESTO);
            s+="\n Total: "+total;
            JOptionPane.showMessageDialog(null, s);
            //meteFacturas(new NodoC(s));
            vaciarPila();
        }
        else{
            JOptionPane.showMessageDialog(null,"No se ha facturado nada");
        }
     }
     
     
     //Cola Informe De Facturas
     
//      public void meteFacturas(NodoC d){
//      if(frente==null){
//         frente=d;
//         ultimo=d;
//      }else{
//         ultimo.setAtras(d);
//         ultimo=d;
//      }
//   }
//   
//   public void informeDeFacturas(){
//      String p="";
//      NodoC auxC=frente;
//      while(auxC!=null){
//         p+=auxC.getDato()+"\n ";
//         auxC=auxC.getAtras();
//      }
//      JOptionPane.showMessageDialog(null,"Informe de Facturas\n"+p);
//   }
  
}
