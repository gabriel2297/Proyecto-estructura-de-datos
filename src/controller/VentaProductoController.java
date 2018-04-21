/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import model.venta.Dato;
import model.venta.Venta;

/**
 *
 * @author gabriel
 */
public class VentaProductoController {
    
    // tabla y columnas
    @FXML
    private TableView<Dato> ventasTable;
    @FXML
    private TableColumn<Dato, String> productoColumn;
    @FXML
    private TableColumn<Dato, Integer> cantidadColumn;
    @FXML 
    private TableColumn<Dato, Double> precioColumn;
//    @FXML 
//    private TableColumn<Dato, Double> precioTotalColumn;

    
    
    // Reference to the main application.
    private MainApp mainApp;
    
    /**
     * Constructor
     */
    public VentaProductoController() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        productoColumn.setCellValueFactory(cellData -> cellData.getValue().getDato().getDato().nombreProperty());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().getDato().getDato().precioVentaProperty().asObject());
        //precioTotalColumn.setCellValueFactory(cellData -> cellData.getValue().precioFinalProperty().asObject());
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        ventasTable.setItems(mainApp.getVentasDato());
    }
        
    @FXML
    private void handleMenuBtn(){
       mainApp.mostrarInicio();   
    }
    
    @FXML
    private void handleVerTotal(){
        double total = 0;
        int tableSize = ventasTable.getItems().size();
        if(tableSize == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Aún no hay datos en venta");
            alert.showAndWait();
        }
        else{
            for(int i=0;i<tableSize;i++){
                total+=ventasTable.getItems().get(i).getPrecioFinal();
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Total a pagar hasta el momento: ₡"+total);
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleAgregarBtn(){
       Dato dato = new Dato();
       boolean okClicked = mainApp.mostrarAgregarVentaDialog(dato);
       if (okClicked){
           // revisar si hay suficientes
           if(!Venta.haySuficientes(dato)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setTitle("Error en agregar");
                alert.setHeaderText("Error al agregar cantidad");
                alert.setContentText("La cantidad indicada es mayor a la que hay en inventario. Por favor agregue más a "
                        + "inventario o corríja la cantidad.");
                alert.showAndWait();
            }else{
                mainApp.getVentasDato().add(dato);
                Venta.nuevaVenta(dato);
            }
       }
    }
    
    @FXML
    private void handleFacturarBtn(){
        if(ventasTable.getItems().size()>0){
            TextInputDialog dialog = new TextInputDialog("");
            Alert alert = new Alert(AlertType.INFORMATION);
            dialog.setTitle("Nombre de factura");
            dialog.setHeaderText("¿Factura a nombre de quién?");
            Optional<String> nombreFactura = dialog.showAndWait();

            double total = 0;
            int tableSize = ventasTable.getItems().size();
            for(int i=0;i<tableSize;i++){
                    total+=ventasTable.getItems().get(i).getPrecioFinal();
            }
            final double IMPUESTO = 0.13;
            double precioFinal = total + (total*IMPUESTO);

            int pagaCon = 0;
            try{
                dialog = new TextInputDialog("");
                dialog.setTitle("Venta terminada satisfactoriamente");
                dialog.setHeaderText("Factura a nombre de: "+nombreFactura.get()+
                        "\n\nMonto a pagar: ₡"+precioFinal
                        +"\n\n¿Con cuánto dinero paga el cliente? Presione 0 si es tarjeta.\n");
                Optional<String> result = dialog.showAndWait();
                pagaCon = Integer.parseInt(result.get());
            }catch(NumberFormatException e){
                e.printStackTrace();
            }

            while(pagaCon<precioFinal && pagaCon!=0){
                dialog = new TextInputDialog("");
                dialog.setTitle("Venta terminada satisfactoriamente");
                dialog.setHeaderText("Factura a nombre de: "+nombreFactura.get()+
                        "\n\nMonto a pagar: ₡"+precioFinal
                        +"\n\nPor favor ingrese un cambio mayor al monto final del cliente. Presione 0 si es tarjeta.\n");
                Optional<String> result = dialog.showAndWait();
                try{
                    pagaCon = Integer.parseInt(result.get());
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }
            }

            if(pagaCon!=0){
                alert.setTitle("Vuelto");
                alert.setHeaderText("Cambio a entregar a cliente: "+(pagaCon-precioFinal));
                alert.showAndWait();
            }    

            Venta.eliminarVenta();
            try{
                ventasTable.getItems().clear();
            }catch(UnsupportedOperationException e){
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Aún no hay datos en venta");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void handleEditarBtn(){
       Dato productoSeleccionado = ventasTable.getSelectionModel().getSelectedItem();
       if (productoSeleccionado != null) {
           boolean okClicked = mainApp.showEditarVentaProductoDialog(productoSeleccionado);
           if (okClicked) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cambio realizado exitosamente");
                alert.setHeaderText(null);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setContentText("El cambio de cantidad concluyo de manera exitosa.");
                alert.showAndWait();
            }
       } else {
           // Nothing selected.
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(mainApp.getProductoVista());
           alert.setTitle("No seleccionó nada");
           alert.setHeaderText("Ningun producto seleccionado");
           alert.setContentText("Por favor seleccione el producto a editar de la tabla");
           alert.showAndWait();
       }       
    }
   
}
