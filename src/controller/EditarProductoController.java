package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class EditarProductoController {

    @FXML
    private TextField nombre_txt;
    @FXML
    private TextField cantidadBodega_txt;
    @FXML
    private TextField precioCompra_txt;
    @FXML
    private TextField precioVenta_txt;


    private Stage editarStage;
    private Producto producto;
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
    public void setDialogStage(Stage editarStage) {
        this.editarStage = editarStage;
    }

    /**
     * Sets the producto to be edited in the dialog.
     * 
     * @param producto
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
        nombre_txt.setText(producto.getNombre());
        cantidadBodega_txt.setText(Integer.toString(producto.getCantidadBodega()));
        precioCompra_txt.setText(Double.toString(producto.getPrecioCompra()));
        precioVenta_txt.setText(Double.toString(producto.getPrecioVenta()));
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
            producto.setNombre(nombre_txt.getText());
            producto.setCantidadBodega(Integer.parseInt(cantidadBodega_txt.getText()));
            producto.setPrecioCompra(Double.parseDouble(precioCompra_txt.getText()));
            producto.setPrecioVenta(Double.parseDouble(precioVenta_txt.getText()));
            okClicked = true;
            editarStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        editarStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombre_txt.getText() == null || nombre_txt.getText().length() == 0) {
            errorMessage += "Nombre no es valido!\n"; 
        }
        if (cantidadBodega_txt.getText() == null || cantidadBodega_txt.getText().length() == 0) {
            errorMessage += "Cantidad no valida\n"; 
        }else{
            // ver si es una entero
            try{
                Integer.parseInt(cantidadBodega_txt.getText());
            } catch(NumberFormatException e){
                errorMessage+= cantidadBodega_txt+" no es valido. Digite solo numeros por favor!\n";
            }
        }
        if (precioCompra_txt.getText() == null || precioCompra_txt.getText().length() == 0) {
            errorMessage += "Precio de compra no valido!\n"; 
        }else{
            // ver si es un double
            try{
                Double.parseDouble(precioCompra_txt.getText());
            }catch(NumberFormatException e){
                errorMessage+= precioCompra_txt.getText()+" no es valido. Por favor solo digite numeros!\n";
            }
        }
        if (precioVenta_txt.getText() == null || precioVenta_txt.getText().length() == 0) {
            errorMessage += "Precio de venta no valido!\n"; 
        }else{
            // ver si es un double
            try{
                Double.parseDouble(precioVenta_txt.getText());
            }catch(NumberFormatException e){
                errorMessage+= precioVenta_txt.getText()+" no es valido. Por favor solo digite numeros!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(editarStage);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor corrija los espacios invalidos");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
