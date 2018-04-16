package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import controller.MainApp;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Producto;
import model.lista.Lista;
import model.lista.Nodo;

public class ProductoVistaController {
    
    // tabla y columnas
    @FXML
    private TableView<Producto> productoTable;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, Integer> codigoColumn;

    // labels
    @FXML
    private Label nombreLabel;
    @FXML
    private Label codigoLabel;
    @FXML
    private Label precioCompraLabel;
    @FXML
    private Label precioVentaLabel;
    @FXML
    private Label cantidadBodegaLabel;

    // button
    @FXML
    private Button menuButton;
    
    // Reference to the main application.
    private MainApp mainApp;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProductoVistaController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        
        // Clear person details.
        showProductoDetails(null);

        // Listen for selection changes and show the person details when changed.
        productoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductoDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        productoTable.setItems(mainApp.getProductoData());
    }
    
    /**
    * Fills all text fields to show details about the person.
    * If the specified person is null, all text fields are cleared.
    * 
    * @param person the person or null
    */
   private void showProductoDetails(Producto producto) {
       if (producto != null) {
           // Fill the labels with info from the person object.
           nombreLabel.setText(producto.getNombre());
           codigoLabel.setText(Integer.toString(producto.getCodigo()));
           cantidadBodegaLabel.setText(Integer.toString(producto.getCantidadBodega()));
           precioCompraLabel.setText(Double.toString(producto.getPrecioCompra()));
           precioVentaLabel.setText(Double.toString(producto.getPrecioVenta()));
       } else {
           // Person is null, remove all the text.
           nombreLabel.setText("");
           codigoLabel.setText("");
           cantidadBodegaLabel.setText("");
           precioCompraLabel.setText("");
           precioVentaLabel.setText("");
       }
   }
   
   /**
    * Called when the user clicks on the delete button.
    */
   @FXML
   private void Eliminar_btnHandle() {
        int selectedIndex = productoTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
            Nodo nodo = Lista.existe(productoSeleccionado.getCodigo());
            Lista.eliminar(nodo);
            productoTable.getItems().remove(selectedIndex);
        }else{
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No hubo ninguna seleccion");
            alert.setHeaderText("No selecciono ningun producto");
            alert.setContentText("Por favor seleccione el producto a eliminar de la tabla");
            alert.showAndWait();
        }
   }

   /**
    * Llamado cuando el usuario le da click al boton de editar.
    * Abre un nuevo dialogo con la info del producto que se quiere editar. 
    */
   @FXML
   private void handleEditarProducto() {
       Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
       if (productoSeleccionado != null) {
           boolean okClicked = mainApp.showEditarProductoDialog(productoSeleccionado);
           if (okClicked) {
               Lista.modificar(productoSeleccionado.getCodigo(), productoSeleccionado.getCantidadBodega(), 
                       productoSeleccionado.getPrecioCompra(), productoSeleccionado.getPrecioVenta(),
                       productoSeleccionado.getNombre());
               showProductoDetails(productoSeleccionado);
           }
       } else {
           // Nothing selected.
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(mainApp.getPrimaryStage());
           alert.setTitle("No seleccionó nada");
           alert.setHeaderText("Ningun producto seleccionado");
           alert.setContentText("Por favor seleccione el producto a editar de la tabla");
           alert.showAndWait();
       }
   }
   
   /**
    * Llamado cuando el usuario quiere agregar un nuevo producto. 
    * Abre un dialog para editar
    */
   @FXML
   private void handleAgregarProducto(){
       Producto producto = new Producto();
       boolean okClicked = mainApp.showAgregarProductoDialog(producto);
       if (okClicked){
           // revisar si es duplicado o no
            if(Lista.esDuplicado(producto.getCodigo())){
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Codigo ya existente");
                alert.setHeaderText("El codigo ya existe");
                alert.setContentText("Por favor utilice un nuevo codigo o borre el producto anterior.");
                alert.showAndWait();
            }else{
                mainApp.getProductoData().add(producto);
                Lista.agregarProducto(producto);
            }
       }
   }
   
   @FXML
   private void handleMenuBtn(){
       mainApp.mostrarInicio();   
   }
}