package controller;

import javafx.fxml.FXML;

/**
 * Clase que maneja el menú de inicio
 * 
 * @author todos
 */
public class InicioController {
    private MainApp mainApp;

    /**
     * Constructor. Llamado despues del método de inicializar
     */
    public InicioController() {
    }

    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     */
    @FXML
    private void initialize() {
    }

     /**
     * Llamado por el main para tener una referencia hacia él mismo
     * @param mainApp - recibe el mainApp de mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Método que se encarga de manejar si el usuario da click al botón de ventas
     * Redirige a ventas
     */
    public void handleVentasBtn(){
        mainApp.mostrarVentasProducto();
    }
    
    /**
     * Método que se encarga de manejar si el usuario da click al botón de producto
     * Redirige a producto
     */
    public void handleProductoBtn(){
        mainApp.mostrarInventarioDeProductos();
    }
    
    /**
     * Método que se encarga de manejar si el usuario da click al botón de facturacion
     * Redirige a facturas
     */
    public void handleFacturacionBtn(){
        mainApp.mostrarFacturacion();
    }
    
    /**
     * Metodo que se encarga de manejar si el usuario da click al boton de empleados
     * Redirige a empleados
     */
    public void handleEmpleadosBtn(){
        mainApp.mostrarEmpleados();
    }
}