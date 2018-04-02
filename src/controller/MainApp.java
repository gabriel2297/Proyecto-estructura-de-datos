package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Producto;
import model.lista.Lista;
import view.AgregarProductoController;
import view.ProductoVistaController;
import view.editarProductoController;

public class MainApp extends Application {

    private Stage productoVista;
    private BorderPane borde;
    Lista lista = new Lista();

    
    /**
     * The data as an observable list of Producto.
     * 
     */
    private ObservableList<Producto> productoData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {

    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Producto> getProductoData() {
        return productoData;
    }
    @Override
    public void start(Stage primaryStage) {
        this.productoVista = primaryStage;
        this.productoVista.setTitle("Inventario");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/Borde.fxml"));
            borde = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(borde);
            productoVista.setScene(scene);
            productoVista.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/ProductoVista.fxml"));
            AnchorPane productoVista = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            borde.setCenter(productoVista);
            
            // Give the controller access to the main app.
            ProductoVistaController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return productoVista;
    }
    
    /**
    * Opens a dialog to edit details for the specified person. If the user
    * clicks OK, the changes are saved into the provided person object and true
    * is returned.
    * 
    * @param producto the person object to be edited
    * @return true if the user clicked OK, false otherwise.
    */
   public boolean showEditarProductoDialog(Producto producto) {
       try {
           // Load the fxml file and create a new stage for the popup dialog.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/editarProductoVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();

           // Create the dialog Stage.
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Editar producto");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(productoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);

           // Set the person into the controller.
           editarProductoController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setProducto(producto);

           // Show the dialog and wait until the user closes it
           dialogStage.showAndWait();

           return controller.isOkClicked();
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }
   }
   
   public boolean showAgregarProductoDialog(Producto producto){
       try{
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/agregarProductoVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();
           
           // crear el nuevo dialogo
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Agregar producto");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(productoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);
           
           // set the product into the controller
           AgregarProductoController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setProducto(producto);
           
           dialogStage.showAndWait();
           
           return controller.isOkClicked();
       }catch(IOException e){
           e.printStackTrace();
           return false;
       }
   }

    public static void main(String[] args) {
        launch(args);
    }
}