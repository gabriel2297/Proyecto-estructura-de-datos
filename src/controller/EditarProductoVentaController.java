package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.venta.Dato;
import model.venta.Venta;

/**
 * Clase que se encarga de editar productos que se están vendiendo. 

 * @author Elena
 */
public class EditarProductoVentaController {
    @FXML
    private TextField nombre_txt;
    @FXML
    private TextField cantidad_txt;


    private Stage editarVentaStage;
    private Dato dato;
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
     * @param editarVentaStage - recibe la ventana principal
     */
    public void setDialogStage(Stage editarVentaStage) {
        this.editarVentaStage = editarVentaStage;
    }

    /**
     * Agrega el dato (producto) que va a ser editato al dialogo.
     * 
     * @param dato - el producto que se va a editar
     */
    public void setDato(Dato dato) {
        this.dato = dato;
        nombre_txt.setText(dato.getDato().getDato().getNombre());
        cantidad_txt.setText(""+dato.getCantidad());
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
     * Luego de darle click, guarda el valor anterior por si se necesita luego de realizar las operaciones.
     * Restaura el inventario a sus valores anteriores, ingresa la nueva cantidad decidida por el usuario. 
     * Si la nueva cantidad es mayor a lo que hay en inventario, mostrará un error y volvera al valor anterior que guardámos.
     * Si no, editará la pila en donde se almacenan las ventas y cerrará el diálogo.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            int valorAnterior = dato.getCantidad();
            int enBodega = dato.getDato().getDato().getCantidadBodega();
            Venta.restaurarInventario(dato.getDato(), dato.getDato().getDato().getCodigo());
            dato.setCantidad(Integer.parseInt(cantidad_txt.getText()));
            if(!Venta.haySuficientes(dato)){
                dato.getDato().getDato().setCantidadBodega(enBodega);
                dato.setCantidad(valorAnterior);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(editarVentaStage);
                alert.setTitle("Error");
                alert.setHeaderText("Error al agregar nueva cantidad");
                alert.setContentText("La nueva cantidad es mayor al stock en inventario. Por favor agregue más"
                        + " cantidad al inventario o intentelo de nuevo.");
                alert.showAndWait();
                okClicked = false;
            }else{
                Venta.editarVenta(dato.getDato().getDato().getCodigo(), Integer.parseInt(cantidad_txt.getText()));
                okClicked = true;
            }
            editarVentaStage.close();
        }
    }

    /**
     * llamado cuando el usuario le da cancelar
     */
    @FXML
    private void handleCancel() {
        editarVentaStage.close();
    }

    /**
     * Método que revisa si los datos que ingresó el usuario son correctos. 
     * @return true si no hubo ningun error. 
     * @return false si hay errores. Indica los errores. 
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (cantidad_txt.getText() == null || cantidad_txt.getText().length() == 0) {
            errorMessage += "Cantidad no valida\n"; 
        }else{
            // ver si es una entero
            try{
                Integer.parseInt(cantidad_txt.getText());
            } catch(NumberFormatException e){
                errorMessage+= cantidad_txt+" no es valido. Digite solo numeros por favor!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editarVentaStage);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor corrija los espacios invalidos");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
