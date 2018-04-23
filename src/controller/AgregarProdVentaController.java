package controller;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
 * Clase AgregarProdVentaController
 * Esta clase se encarga de controlar todas las acciones que realiza un usuario
 * a la hora de agregar un producto a pila de venta.

 * @author Elena
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

    // choicebox
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
    private Dato dato;
    private boolean okClicked= false;
    
    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     * Inicializa la tabla con sus columnas, pone la descripcion de productos seleccionados en null ya que 
     * no hay ninguno seleccionado en el momento de inicializar y agrega un choicebox donde se puede
     * buscar un producto por codigo o por nombre del producto
     * 
     * Así mismo cuenta con un action listener que escucha a cambios en la selección de productos. Cuando detecta un cambio
     * inmediatamente cambia el producto que se está manipulando.
     */
    @FXML
    private void initialize(){
        // inicializar la tabla de productos con 3 columnas
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadBodegaProperty().asObject());
        // limpiar los detalles de producto
        mostrarProductoSeleccionado(null);
        // agregar el choicebox
        choiceBox.getItems().addAll("Buscar Producto", "Buscar Codigo");
        choiceBox.setValue("Buscar Producto");
        // agregar el textfield
        busqueda.setPromptText("Busque aqui!");
        busqueda.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())// switch con el valor del choicebox seleccionado
            {
                case "Buscar Producto":
                    // Filtra la tabla por el nombre
                    productos.setPredicate(p -> p.getNombre().toLowerCase().contains(busqueda.getText().toLowerCase().trim()));
                    break;
                case "Buscar Codigo":
                    // Filtra tabla por codigo
                    try{
                        productos.setPredicate(p -> p.getCodigo() == Integer.parseInt(busqueda.getText()));
                    }
                    catch(NumberFormatException e){
                        System.out.println("Error");
                    }
                    break;
            }
        });
        // cambiar la tabla al seleccionar un nuevo producto
        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null)
            {
                busqueda.setText("");
                productos.setPredicate(null);
            }
        });
        // metodo que escucha por cambios en seleccion y cambia dinamicamente la seleccion
        productoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarProductoSeleccionado(newValue));
    }

    /**
     * Pone la ventana principal (a quien le pertenece) este dialogo
     * Crea una listra filtrada de los productos para luego poder filtrarlos con el choicebox.
     * Además, inserta a la tabla los productos que están en inventario.
     * 
     * @param agregarVentaStage - recibe la ventana principal
     * @param mainApp - recibe el mainApp para accesar a los productos
     */
    public void setDialogStage(Stage agregarVentaStage, MainApp mainApp) {
        this.agregarVentaStage = agregarVentaStage;
        this.mainApp = mainApp;
        productos = new FilteredList(mainApp.getProductoData(), p -> true);
        productoTable.setItems(productos);
    }
    
    /**
     * Metodo que se encarga de cambiar la información de un nuevo dato.
     * 
     * @param dato - recibe el dato que queremos agregar. 
     */
    public void setDato(Dato dato){
        this.dato = dato;
    }
    
    /**
     * Método que retorna true si el usuario le dio click a OK. 
     * 
     * @return true si la persona le dio click a ok
     * @return false si la persona no le dio click a ok.
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
    * Método que llena los textfields con los datos del producto seleccionado.
    * Si no se ha seleccionado ningun producto va a poner el textfield en blanco.
    * 
    * @param person - recibe el producto que se seleccionó o null
    */
   private void mostrarProductoSeleccionado(Producto producto) {
       if (producto != null) {
           nombreLabel.setText(producto.getNombre());
       } else {
           nombreLabel.setText("");
       }
   }
    
    /**
     * Método que es llamado para manejar cuando el usuario le da click a cancelar.
     */
    @FXML
    private void handleCancel() {
        agregarVentaStage.close();
    }
    
    /**
     * Manejar cuando el usuario le da click a agregar.
     * Obtiene el producto seleccionado or el usuario, crea un nuevo nodo con ese producto y agrega la cantidad 
     * que el usuario ingreso para dicho producto. Cierra la ventana (dialog) al terminar.
     */
    public void handleAgregarBtn(){
        Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
        Nodo nodo = new Nodo(productoSeleccionado);
        if(productoSeleccionado!=null){
            if(isInputValid()){
                dato.setCantidad(Integer.parseInt(cantidad.getText()));
                dato.setDato(nodo);
                okClicked = true;
            }
        }
        agregarVentaStage.close();
    }
    
    /**
     * Método que revisa si los datos que ingresó el usuario son correctos. 
     * @return true si no hubo ningun error. 
     * @return false si hay errores. Indica los errores. 
     */
    private boolean isInputValid(){
        String errorMessage = "";
        if (cantidad.getText() == null || cantidad.getText().length() == 0) {
            errorMessage += "Cantidad no es valida\n";
        }else{
            // ver si es un entero
            try{
                Integer.parseInt(cantidad.getText());
            } catch(NumberFormatException e){
                errorMessage+= cantidad.getText()+" no es valida. Digite solo numeros por favor!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // mostrar los errores
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(agregarVentaStage);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor corrija los espacios invalidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
