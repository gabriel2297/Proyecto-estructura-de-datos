package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;


/**
 * Clase que se encarga de agregar productos al inventario. 
 * 
 * @author Gabriel
 */
public class AgregarProductoController {

    // textfields
    @FXML
    private TextField nombre_txt;
    @FXML
    private TextField cantidadBodega_txt;
    @FXML
    private TextField precioCompra_txt;
    @FXML
    private TextField precioVenta_txt;
    @FXML
    private TextField codigo_txt;

    
    private Stage agregarStage;
    private Producto producto;
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
     * @param agregarStage - recibe la ventana principal
     */
    public void setDialogStage(Stage agregarStage) {
        this.agregarStage = agregarStage;
    }

    /**
     * Agrega el producto que va a ser editato al dialogo.
     * 
     * @param producto - el producto que se va a editar
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
        nombre_txt.setText(producto.getNombre());
        codigo_txt.setText(Integer.toString(producto.getCodigo()));
        cantidadBodega_txt.setText(Integer.toString(producto.getCantidadBodega()));
        precioCompra_txt.setText(Double.toString(producto.getPrecioCompra()));
        precioVenta_txt.setText(Double.toString(producto.getPrecioVenta()));
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
    private void handleOk() {
        if (isInputValid()) {
            producto.setNombre(nombre_txt.getText());
            producto.setCodigo(Integer.parseInt(codigo_txt.getText()));
            producto.setCantidadBodega(Integer.parseInt(cantidadBodega_txt.getText()));
            producto.setPrecioCompra(Double.parseDouble(precioCompra_txt.getText()));
            producto.setPrecioVenta(Double.parseDouble(precioVenta_txt.getText()));
            okClicked = true;
            agregarStage.close();
        }
    }

    /**
     * llamado cuando el usuario le da cancelar
     */
    @FXML
    private void handleCancel() {
        agregarStage.close();
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
            // ver si es una entero
            try{
                Integer.parseInt(codigo_txt.getText());
            } catch(NumberFormatException e){
                errorMessage+= codigo_txt.getText()+" no es valido. Digite solo numeros por favor!\n";
            }
        }
        if (cantidadBodega_txt.getText() == null || cantidadBodega_txt.getText().length() == 0) {
            errorMessage += "Cantidad no valida\n"; 
        }else{
            // ver si es una entero
            try{
                Integer.parseInt(cantidadBodega_txt.getText());
            } catch(NumberFormatException e){
                errorMessage+= cantidadBodega_txt.getText()+" no es valido. Digite solo numeros por favor!\n";
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
            // mostrar los errores
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(agregarStage);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor corrija los espacios invalidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
