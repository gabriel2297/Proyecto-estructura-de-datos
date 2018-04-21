package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;
import model.lista.Nodo;
import model.venta.Dato;
import model.venta.Venta;

/**
 *
 * @author gabriek
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
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param editarStage
     */
    public void setDialogStage(Stage editarVentaStage) {
        this.editarVentaStage = editarVentaStage;
    }

    /**
     * Sets the producto to be edited in the dialog.
     * 
     * @param producto
     */
    public void setDato(Dato dato) {
        this.dato = dato;
        nombre_txt.setText(dato.getDato().getDato().getNombre());
        cantidad_txt.setText(""+dato.getCantidad());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            int valorAnterior = dato.getCantidad();
            Venta.restaurarInventario(dato.getDato(), dato.getDato().getDato().getCodigo());
            dato.setCantidad(Integer.parseInt(cantidad_txt.getText()));
            if(!Venta.haySuficientes(dato)){
                dato.setCantidad(valorAnterior);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(editarVentaStage);
                alert.setTitle("Error");
                alert.setHeaderText("Error al agregar nueva cantidad");
                alert.setContentText("La nueva cantidad es mayor al stock en inventario. Por favor agregue más"
                        + " cantidad al inventario o intentelo de nuevo.");
                alert.showAndWait();
            }else{
                Venta.editarVenta(dato.getDato().getDato().getCodigo(), Integer.parseInt(cantidad_txt.getText()));
            }
            okClicked = true;
            editarVentaStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        editarVentaStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
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
