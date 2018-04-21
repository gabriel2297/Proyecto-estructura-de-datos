/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class FacturacionVistaController{
    // tabla y columnas
    @FXML
    private TableView<Cliente> facturasTable;
    @FXML
    private TableColumn<Cliente, String> clienteColumn;

    // labels
    @FXML
    private Label nombreLabel;
    @FXML
    private Label fechaCompraLabel;
    @FXML
    private Label totalLabel;
    
    // Reference to the main application.
    private MainApp mainApp;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public FacturacionVistaController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        clienteColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        
        // Clear person details.
        mostrarDetallesFactura(null);

        // Listen for selection changes and show the person details when changed.
        facturasTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesFactura(newValue));
    }

    /**
     * Is called 
     * by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        facturasTable.setItems(mainApp.getFacturaDatos());
    }
    

    
    /**
    * Fills all text fields to show details about the person.
    * If the specified person is null, all text fields are cleared.
    * 
    * @param person the person or null
    */
   private void mostrarDetallesFactura(Cliente cliente) {
       if (cliente != null) {
           // Fill the labels with info from the person object.
           nombreLabel.setText(cliente.getNombre());
           totalLabel.setText((Double.toString(cliente.getTotal())));
           fechaCompraLabel.setText(cambiarFecha.format(cliente.getFechaCompra()));
       } else {
           // Person is null, remove all the text.
           nombreLabel.setText("");
           totalLabel.setText("");
           fechaCompraLabel.setText("");
       }
   }
   
   @FXML
   private void handleMenuBtn(){
       mainApp.mostrarInicio();   
   }
}
