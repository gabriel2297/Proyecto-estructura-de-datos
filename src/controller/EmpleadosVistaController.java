package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Empleado;
import model.empleados.Empleados;

/**
 * Clase que controla todas las funciones que se realizan con el manejo de empleados
 *
 * @author Alonso
 */
public class EmpleadosVistaController{
    
    // tabla y columnas
    @FXML
    private TableView<Empleado> empleadosTable;
    @FXML
    private TableColumn<Empleado, String> nombreColumn;
    @FXML
    private TableColumn<Empleado, Integer> codigoColumn;

    // labels
    @FXML
    private Label nombreLabel;
    @FXML
    private Label codigoLabel;
    @FXML
    private Label salarioLabel;
    @FXML
    private Label puestoLabel;
    @FXML
    private Label contratadorLabel;

    private MainApp mainApp;


    /**
     * Constructor por defecto. 
     * Llamado antes de que se llame el método de inicializar.
     */
    public EmpleadosVistaController() {
    }

    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     * Inicializa la tabla con la información de nombre del empleado y el código del empleado
     * Inicializa los detalles del empleado con null.
     * y escucha por cambios en la selección de empleado de ser el caso.
     */
    @FXML
    private void initialize() {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty().asObject());
        
        mostrarDetallesEmpleado(null);

        empleadosTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesEmpleado(newValue));
    }

    /**
     * Llamado por el main para tener una referencia hacia él mismo
     * Inicializa la tabla de empleados con la información almacenada en el arreglo de empleados en Main.
     * @param mainApp - recibe el mainApp de mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        empleadosTable.setItems(mainApp.getEmpleadoDatos());
    }
    
    /**
    * Método que llena los textfields con los datos del empleado seleccionado.
    * Si no se ha seleccionado ningun empleado va a poner el textfield en blanco.
    * 
    * @param producto - recibe el empleado que se seleccionó o null
    */
   private void mostrarDetallesEmpleado(Empleado empleado) {
       if (empleado != null) {
           nombreLabel.setText(empleado.getNombre());
           codigoLabel.setText(Integer.toString(empleado.getCodigo()));
           salarioLabel.setText(Double.toString(empleado.getSalario()));
           puestoLabel.setText(empleado.getPuesto());
           contratadorLabel.setText(empleado.getContratador());
       } else {
           nombreLabel.setText("");
           codigoLabel.setText("");
           salarioLabel.setText("");
           puestoLabel.setText("");
           contratadorLabel.setText("");
       }
   }
   
   /**
    * Método que maneja cuando la presona le da click a eliminar producto. 
    * Primero obtiene el producto seleccionado, luego verifica que ese producto exista en la lista 
    * Y procede a eliminarlo de la lista y tabla. 
    * Muestra error si ningun producto es seleccionado.
    */
    @FXML
    private void manejarEliminarBtn(){
        int selectedIndex = empleadosTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            Empleado productoSeleccionado = empleadosTable.getSelectionModel().getSelectedItem();
            empleadosTable.getItems().remove(selectedIndex);
            Empleados.eliminaEmpleado(productoSeleccionado);
        }else{
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getEmpleadoVista());
            alert.setTitle("No hubo ninguna seleccion");
            alert.setHeaderText("No selecciono ningun empleado");
            alert.setContentText("Por favor seleccione el empleado a eliminar de la tabla");
            alert.showAndWait();
        }
    }

   
   /**
    * Llamado cuando el usuario quiere agregar un nuevo producto. 
    * Abre un dialog para agregar
    */
    @FXML
    private void manejarAgregarBtn(){
       Empleado empleado = new Empleado();
       boolean okClicked = mainApp.mostrarAgregarEmpleadoDialogo(empleado);
       if (okClicked){
            mainApp.getEmpleadoDatos().add(empleado);
            Empleados.creaEmpleado(empleado);
       }
    }
   
   /**
    * Llamado para volver al menú principal
    */
   @FXML
   private void manejarMenuBtn(){
       mainApp.mostrarInicio();   
   }
} 
    
