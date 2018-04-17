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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;

/**
 *
 * @author gabriek
 */
public class AgregarProdVentaController {
    
    // tabla y columnas
    @FXML
    private TableView<Producto> productoTable;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, Integer> codigoColumn;
    @FXML
    private TableColumn<Producto, Integer> cantidadColumn;

    // labels
    @FXML
    private Label nombreLabel;

    
    // Reference to the main application.
    private MainApp mainApp;
    
    private Stage agregarVentaStage;
    private Producto producto;
    private boolean okClicked= false;

    public AgregarProdVentaController(){}
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadBodegaProperty().asObject());
        // Clear person details.
        mostrarProductoSeleccionado(null);

        // Listen for selection changes and show the person details when changed.
        productoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarProductoSeleccionado(newValue));
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param agregarVentaStage
     */
    public void setDialogStage(Stage agregarVentaStage, MainApp mainApp) {
        this.agregarVentaStage = agregarVentaStage;
        this.mainApp = mainApp;
        productoTable.setItems(mainApp.getProductoData());
    }
    
    /**
    * Fills all text fields to show details about the person.
    * If the specified person is null, all text fields are cleared.
    * 
    * @param person the person or null
    */
   private void mostrarProductoSeleccionado(Producto producto) {
       if (producto != null) {
           // Fill the labels with info from the person object.
           nombreLabel.setText(producto.getNombre());
       } else {
           // Person is null, remove all the text.
           nombreLabel.setText("");
       }
   }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        agregarVentaStage.close();
    }
    
}
