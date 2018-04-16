/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Producto;

/**
 *
 * @author gabriel
 */
public class VentaProductoController {
    
    // tabla y columnas
    @FXML
    private TableView<Producto> ventasTable;
    @FXML
    private TableColumn<Producto, String> productoColumn;
    @FXML
    private TableColumn<Producto, Integer> cantidadColumn;
    @FXML 
    private TableColumn<Producto, Integer> precioColumn;
    @FXML 
    private TableColumn<Producto, Integer> precioTotalColumn;
    
    // labels
    @FXML
    private Label totalPagarLabel;

    // button
    @FXML
    private Button menuButton;
    
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
        //productoColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        //cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        
        // Clear person details.
        //showProductoDetails(null);

        // Listen for selection changes and show the person details when changed.
        //ventasTable.getSelectionModel().selectedItemProperty().addListener(
                //(observable, oldValue, newValue) -> showProductoDetails(newValue));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        // ventasTable.setItems(mainApp.getProductoData());
    }
    
    @FXML
   private void handleMenuBtn(){
       mainApp.mostrarInicio();   
   }
    
}
