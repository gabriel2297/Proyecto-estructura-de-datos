/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;
import model.lista.Nodo;
import model.venta.Dato;

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

    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML
    private TextField busqueda;
    @FXML
    private TextField cantidad;
    // labels
    @FXML
    private Label nombreLabel;
    
    // Reference to the main application.
    private MainApp mainApp;

    // pasar la informacion a un filteredList
    FilteredList<Producto> productos;
    
    private Stage agregarVentaStage;
    private Producto producto;
    private boolean okClicked= false;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize(){
        // Initialize the person table with the two columns.
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadBodegaProperty().asObject());
        // Clear person details.
        mostrarProductoSeleccionado(null);
        
        // agregar el choicebox
        choiceBox.getItems().addAll("Buscar Producto", "Buscar Codigo");
        choiceBox.setValue("Buscar Producto");

        // agregar el textfield
        busqueda.setPromptText("Busque aqui!");
        
        busqueda.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "Buscar Producto":
                    productos.setPredicate(p -> p.getNombre().toLowerCase().contains(busqueda.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Buscar Codigo":
                    try{
                        productos.setPredicate(p -> p.getCodigo() == Integer.parseInt(busqueda.getText()));//filter table by first name
                    }
                    catch(NumberFormatException e){
                        System.out.println("Error");
                    }
                    break;
            }
        });
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                busqueda.setText("");
                productos.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
        
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
        productos = new FilteredList(mainApp.getProductoData(), p -> true);
        productoTable.setItems(productos);
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
