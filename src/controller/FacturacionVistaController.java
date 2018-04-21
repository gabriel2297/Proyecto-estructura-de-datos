package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Cliente;

/**
 * Clase que se encarga de mantener la facturación de productos.
 * 
 * @author Alonso
 */
public class FacturacionVistaController{
    // tabla y columnas
    @FXML
    private TableView<Cliente> facturasTable;
    @FXML
    private TableColumn<Cliente, String> clienteColumn;

    // labels
    @FXML
    private Label nombreLabel;
    @FXML
    private Label fechaCompraLabel;
    @FXML
    private Label totalLabel;
    
    // referencia a la app principal.
    private MainApp mainApp;


    /**
     * Constructor por defecto. 
     * Llamado antes de que se llame el método de inicializar.
     */
    public FacturacionVistaController() {
    }

    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     * Inicializa la tabla con la información de nombre de la factura, inicializa los detalles de la factura
     * y escucha por cambios en la selección de facturas de ser el caso.
     */
    @FXML
    private void initialize() {
        clienteColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        
        mostrarDetallesFactura(null);

        facturasTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesFactura(newValue));
    }

    /**
     * Llamado por el main para tener una referencia hacia él mismo
     * Inicializa la tabla de facturas con la información almacenada en el arreglo de facturas.
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        facturasTable.setItems(mainApp.getFacturaDatos());
    }
    

    
    /**
    * Método que llena los textfields con los datos del cliente seleccionado.
    * Si no se ha seleccionado ninguna factura va a poner el textfield en blanco.
    * 
    * @param cliente - recibe el cliente que se seleccionó o null
    */
   private void mostrarDetallesFactura(Cliente cliente) {
       if (cliente != null) {
           nombreLabel.setText(cliente.getNombre());
           totalLabel.setText((Double.toString(cliente.getTotal())));
           fechaCompraLabel.setText(cambiarFecha.format(cliente.getFechaCompra()));
       } else {
           nombreLabel.setText("");
           totalLabel.setText("");
           fechaCompraLabel.setText("");
       }
   }
   
   /**
    * Método que maneja el botón de volver a menú.
    */
   @FXML
   private void handleMenuBtn(){
       mainApp.mostrarInicio();   
   }
}
