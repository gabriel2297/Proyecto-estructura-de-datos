/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML 
    private TableColumn<Dato, Double> precioTotalColumn;

    
    
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
        precioTotalColumn.setCellValueFactory(cellData -> cellData.getValue().precioFinalProperty().asObject());
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
   
}
