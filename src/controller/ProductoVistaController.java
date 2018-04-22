package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Producto;
import model.lista.Lista;
import model.lista.Nodo;

/**
 * Clase que se encarga de mantener el inventario
 * 
 * @author Elena
 */
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

    private MainApp mainApp;


    /**
     * Constructor por defecto. 
     * Llamado antes de que se llame el método de inicializar.
     */
    public ProductoVistaController() {
    }

    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     * Inicializa la tabla con la información de nombre del producto y el código del producto
     * Inicializa los detalles del producto con null.
     * y escucha por cambios en la selección de produtos de ser el caso.
     */
    @FXML
    private void initialize() {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        
        mostrarDetallesProducto(null);

        productoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesProducto(newValue));
    }

    /**
     * Llamado por el main para tener una referencia hacia él mismo
     * Inicializa la tabla de productos con la información almacenada en el arreglo de productos en Main.
     * @param mainApp - recibe el mainApp de mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        productoTable.setItems(mainApp.getProductoData());
    }
    

    
    /**
    * Método que llena los textfields con los datos del producto seleccionado.
    * Si no se ha seleccionado ningun producto va a poner el textfield en blanco.
    * 
    * @param producto - recibe el producto que se seleccionó o null
    */
   private void mostrarDetallesProducto(Producto producto) {
       if (producto != null) {
           nombreLabel.setText(producto.getNombre());
           codigoLabel.setText(Integer.toString(producto.getCodigo()));
           cantidadBodegaLabel.setText(Integer.toString(producto.getCantidadBodega()));
           precioCompraLabel.setText(Double.toString(producto.getPrecioCompra()));
           precioVentaLabel.setText(Double.toString(producto.getPrecioVenta()));
       } else {
           nombreLabel.setText("");
           codigoLabel.setText("");
           cantidadBodegaLabel.setText("");
           precioCompraLabel.setText("");
           precioVentaLabel.setText("");
       }
   }
   
   /**
    * Método que maneja cuando la presona le da click a eliminar producto. 
    * Primero obtiene el producto seleccionado, luego verifica que ese producto exista en la lista 
    * Y procede a eliminarlo de la lista y tabla. 
    * Muestra error si ningun producto es seleccionado.
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
            alert.initOwner(mainApp.getProductoVista());
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
           boolean okClicked = mainApp.mostrarEditarProductoDialogo(productoSeleccionado);
           if (okClicked) {
               Lista.modificar(productoSeleccionado.getCodigo(), productoSeleccionado.getCantidadBodega(), 
                       productoSeleccionado.getPrecioCompra(), productoSeleccionado.getPrecioVenta(),
                       productoSeleccionado.getNombre());
               mostrarDetallesProducto(productoSeleccionado);
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
   
   /**
    * Llamado cuando el usuario quiere agregar un nuevo producto. 
    * Abre un dialog para agregar
    */
   @FXML
   private void handleAgregarProducto() {
       Producto producto = new Producto();
       boolean okClicked = mainApp.mostrarAgregarProductoDialogo(producto);
       if (okClicked){
           // revisar si es duplicado o no
            if(Lista.esDuplicado(producto.getCodigo())){
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getProductoVista());
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
   
   /**
    * Llamado para volver al menú principal
    */
   @FXML
   private void handleMenuBtn(){
       mainApp.mostrarInicio();   
   }
}