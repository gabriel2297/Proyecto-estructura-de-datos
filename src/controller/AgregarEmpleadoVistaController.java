package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Empleado;
import model.Producto;


/**
 * Clase que se encarga de manejar la interaccion entre usuario y 
 * el dialogo de agregar empleado
 *
 * @author Alonso
 */
public class AgregarEmpleadoVistaController  {
    
    // textfields
    @FXML
    private TextField nombre_txt;
    @FXML
    private TextField codigo_txt;
    @FXML
    private TextField salario_txt;
    @FXML
    private TextField puesto_txt;
    @FXML
    private TextField contratante_txt;

    
    private Stage agregarEmpleadoStage;
    private Empleado empleado;
    private boolean okClicked= false;
    
    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     * 
     */
    @FXML
    private void initialize() {
    }

    /**
     * Pone la ventana principal (a quien le pertenece) este dialogo
     * 
     * @param agregarEmpleadoStage - recibe la ventana principal
     */
    public void setDialogStage(Stage agregarEmpleadoStage) {
        this.agregarEmpleadoStage = agregarEmpleadoStage;
    }

    /**
     * Agrega el producto que va a ser agregado al dialogo.
     * 
     * @param empleado - el empleado que se va a editar
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        nombre_txt.setText("");
        codigo_txt.setText("");
        salario_txt.setText("");
        puesto_txt.setText("");
        contratante_txt.setText("");
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
     * Llamado para manejar cuando el usuario le da click a OK. 
     * Luego de darle click, agregará los datos que introdujo el usuario al producto y cerrará el dialogo..
     */
    @FXML
    private void manejarAgregar() {
        if (isInputValid()) {
            empleado.setNombre(nombre_txt.getText());
            empleado.setCodigo(Integer.parseInt(codigo_txt.getText()));
            empleado.setContratador(contratante_txt.getText());
            empleado.setPuesto(puesto_txt.getText());
            empleado.setSalario(Double.parseDouble(salario_txt.getText()));
            okClicked = true;
            agregarEmpleadoStage.close();
        }
    }

    /**
     * llamado cuando el usuario le da cancelar
     */
    @FXML
    private void manejarCancelar() {
        agregarEmpleadoStage.close();
    }

    /**
     * Método que revisa si los datos que ingresó el usuario son correctos. 
     * @return true si no hubo ningun error. 
     * @return false si hay errores. Indica los errores. 
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (nombre_txt.getText() == null || nombre_txt.getText().length() == 0) {
            errorMessage += "Nombre no es valido!\n"; 
        }
        if (codigo_txt.getText() == null || codigo_txt.getText().length() == 0) {
            errorMessage += "Codigo no valido\n";
        }else{
            // ver si es entero
            try{
                Integer.parseInt(codigo_txt.getText());
            }catch(NumberFormatException e){
                errorMessage += codigo_txt.getText()+" no es un numero valido. Digite solo numeros por favor!\n";
            }
        }
        if (salario_txt.getText() == null || salario_txt.getText().length() == 0) {
            errorMessage += "Salario no valida\n"; 
        }else{
            // ver si es una entero
            try{
                Double.parseDouble(salario_txt.getText());
            } catch(NumberFormatException e){
                errorMessage+= salario_txt.getText()+" no es valido. Digite solo numeros por favor!\n";
            }
        }
        if (puesto_txt.getText() == null || puesto_txt.getText().length() == 0) {
            errorMessage += "Puesto no valido!\n"; 
        }
        if (contratante_txt.getText() == null || contratante_txt.getText().length() == 0) {
            errorMessage += "Precio de venta no valido!\n"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // mostrar los errores
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(agregarEmpleadoStage);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor corrija los espacios invalidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
