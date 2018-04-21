package controller;

import javafx.fxml.FXML;

public class InicioController {
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public InicioController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void handleVentasBtn(){
        mainApp.mostrarVentasProducto();
    }
    
    public void handleProductoBtn(){
        mainApp.showPersonOverview();
    }
    
    public void handleFacturacionBtn(){
        mainApp.mostrarFacturacion();
    }
}