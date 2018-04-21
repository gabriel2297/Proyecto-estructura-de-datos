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
import model.Cliente;
import model.Empleado;
import model.Producto;
import model.lista.Lista;
import model.venta.Dato;

/**
 * Clase principal encargada de llevar todos los llamados a métodos y llevar los arreglos
 * 
 * @author todos
 */
public class MainApp extends Application {

    private Stage productoVista;
    private Stage ventaProductoVista;
    private Stage empleadoVista;
    private Stage inicio;
    private BorderPane borde;
    Lista lista = new Lista();

    
    /**
     * ArrayList que guarda los datos de productos en inventario como una lista observable
     */
    private ObservableList<Producto> productoDatos = FXCollections.observableArrayList();
    
    
    /**
     * ArrayList que guarda los datos de productos en venta como una lista observable
     */
    private ObservableList<Dato> ventaDatos = FXCollections.observableArrayList();
    
    /**
     * ArrayList que guarda los datos de cliente para las facturas como una lista observable
     */
    private ObservableList<Cliente> facturaDatos = FXCollections.observableArrayList();
    
    /**
     * Arraylist que guarda los datos de empleados como una lista observable
     */
    private ObservableList<Empleado> empleadoDatos = FXCollections.observableArrayList();
    
    /**
     * Constructor
     */
    public MainApp() {
    }

    /**
     * Método que devuelve los productos como un arraylist
     * @return productoData 
     */
    public ObservableList<Producto> getProductoData() {
        return productoDatos;
    }
    
    /**
     * Metodo que devuelve los datos en venta como un arraylist
     * @return ventaDatos
     */
    public ObservableList<Dato> getVentasDato(){
        return ventaDatos;
    }
    
    /**
     * Metodo que devuelve los datos en factura como un arraylist
     * @return facturaDatos
     */
    public ObservableList<Cliente> getFacturaDatos(){
        return facturaDatos;
    }
    
    /**
     * Metodo que devuelve los datos en empleado como un arraylist
     * @return empleadoDatos
     */
    public ObservableList<Empleado> getEmpleadoDatos(){
        return empleadoDatos;
    }
    
    /**
     * Metodo que inicializa la aplicacion. Muestra el menu de inicio y llena datos en producto para testear
     * @param primaryStage - recibe el primeryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.inicio = primaryStage;
        this.inicio.setTitle("Inventario");

        // datos para testear de productos
        for(int i=65;i<85;i++){
            Producto prod = new Producto("Producto "+(char)i,i);
            productoDatos.add(prod);
            Lista.agregarProducto(prod);
        }
        Producto prod = new Producto("Coca cola 1 litro",100);
        Producto prod2 = new Producto("Coca cola 2 litros",200);
        Producto prod3 = new Producto("Coca cola 3 litro",400);
        Producto prod4 = new Producto("Coccaina",500);
        Lista.agregarProducto(prod);
        Lista.agregarProducto(prod2);
        Lista.agregarProducto(prod3);
        Lista.agregarProducto(prod4);
        productoDatos.addAll(prod,prod2,prod3,prod4);
        
        borde();
        mostrarInicio();
        
    }

    /**
     * Metodo que inicializa el borde de la aplicacion
     */
    public void borde() {
        try {
            // cargar el fxml del borde
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/Borde.fxml"));
            borde = (BorderPane) loader.load();

            // muestra la escena contenida dentro del borde
            Scene scene = new Scene(borde);
            inicio.setScene(scene);
            inicio.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // metodos de ventanas principales (stages)
    
    /**
     * Metodo que se encarga de mostrar el inicio de la aplicacion.
     */
    public void mostrarInicio(){
        try{
            // cargar el inicio
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/Inicio.fxml"));
            AnchorPane inicio = (AnchorPane) loader.load();
            
            // ponerle el borde al inicio
            borde.setCenter(inicio);
            
            InicioController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Muestra el inventario de productos.
     */
    public void mostrarInventarioDeProductos() {
        try {
            // cargar la vista del producto fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/ProductoVista.fxml"));
            AnchorPane productoVista = (AnchorPane) loader.load();

            // pone el inventario en el centro del borde
            borde.setCenter(productoVista);
            
            // le da acceso al controlador
            ProductoVistaController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Muestra la pantalla de ventas de productos
     */
    public void mostrarVentasProducto(){
       try{
           // cargar la pantalla de venta
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/VentaProductoVista.fxml"));
           AnchorPane ventasProducto = (AnchorPane) loader.load();
           
           // poner el scene de productos en el medio de root layout
           borde.setCenter(ventasProducto);
           
           // darle al controlador acceso al mainApp
           VentaProductoController controller = loader.getController();
           controller.setMainApp(this);
       }catch(IOException e){
           e.printStackTrace();
       }
   }
   
   /**
    * Muestra la pantalla de facturacion
    */
   public void mostrarFacturacion() {
        try{
            // cargar la pantalla de facturacion
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/FacturacionVista.fxml"));
            AnchorPane facturacion = (AnchorPane) loader.load();
            
            // poner el scene de facturacion en el medio del borde
            borde.setCenter(facturacion);
            
            // darle al controlador acceso al mainApp
            FacturacionVistaController controller = loader.getController();
            controller.setMainApp(this);
        }catch(IOException e){
            e.printStackTrace();
        }
   }
   
   /**
    * Mostrar pantalla de empleados
    */
   public void mostrarEmpleados(){
       try{
           // cargar la pantalla de empleados
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/EmpleadosVista.fxml"));
           AnchorPane empleados = (AnchorPane) loader.load();
           
           // poner el scene de empleados en el medio del borde
           borde.setCenter(empleados);
           
           // pasarle accceso al controlador
           EmpleadosVistaController controller = loader.getController();
           controller.setMainApp(this);
       }catch(IOException e){
           e.printStackTrace();
       }
   }

    /**
     * Devuelve el stage de producto
     * @return productoVista - el stage
     */
    public Stage getProductoVista() {
        return productoVista;
    }
    
    /**
     * Devuelve el stage de ventas
     * @return ventaProductoVista - el stage
     */
    public Stage getVentaProductoVista(){
        return ventaProductoVista;
    }
    
    /**
     * Devuelve el stage de vista
     * @return empleadoVista - el stage
     */
    public Stage getEmpleadoVista(){
        return empleadoVista;
    }
    
    // metodos de editar o agregar que abren dialogos
    
    /**
    * Abre un dialogo para editar los detalles del producto especifico.
    * Si el usuario da click a OK guarda los cambios en el objeto producto y retorna true
    * 
    * @param producto - el objeto de producto que se va a editar
    * @return true si el usuario presiona OK, false de lo contrario
    */
   public boolean mostrarEditarProductoDialogo(Producto producto) {
       try {
           // cargar el fxml
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/EditarProductoVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();

           // crea un nuevo dialogo para producto editar
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Editar producto");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(productoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);

           // pone el producto en el controlador
           EditarProductoController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setProducto(producto);

           // muestra el dialogo y espera   
           dialogStage.showAndWait();

           return controller.isOkClicked();
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }
   }
   
   /**
    * Abre un dialogo para agregar un nuevo producto
    * Si el usuario da click a OK guarda los cambios en el objeto producto y retorna true
    * 
    * @param producto - el objeto de producto que se va a agregar
    * @return true si el usuario presiona OK, false de lo contrario
    */
   public boolean mostrarAgregarProductoDialogo(Producto producto){
       try{
           // cargar el fxml
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/AgregarProductoVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();
           
           // crear el nuevo dialogo
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Agregar producto");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(productoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);
           
           // le pasa el producto al controlador
           AgregarProductoController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setProducto(producto);
           
           // abre el dialogo y espera respuesta del dialogo.
           dialogStage.showAndWait();
           
           return controller.isOkClicked();
       }catch(IOException e){
           e.printStackTrace();
           return false;
       }
   }
   
   /**
    * Abre un dialogo para agregar un nuevo producto a ventas
    * Si el usuario da click a OK guarda los cambios en el objeto dato y retorna true
    * 
    * @param dato - el objeto de producto que se va a agregar
    * @return true si el usuario presiona OK, false de lo contrario
    */
   public boolean mostrarAgregarVentaDialog(Dato dato){
       try{
           // carga la pantalla
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/AgregarVentaProductoVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();
           
           // crear el nuevo dialogo
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Agregar productos de venta");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(ventaProductoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);
           
           // pasa el objeto que se va a agregar al controlador
           AgregarProdVentaController controller = loader.getController();
           controller.setDialogStage(dialogStage, this);
           controller.setDato(dato);
           
           
           dialogStage.showAndWait();
           
           return controller.isOkClicked();
       }catch(IOException e){
           e.printStackTrace();
           return false;
       }
   }
   
   /**
    * Abre un dialogo para editar la cantidad de uno de los productos en venta
    * Si el usuario da click a OK guarda los cambios en el objeto dato y retorna true
    * 
    * @param dato - el objeto de venta que se va a editar
    * @return true si el usuario presiona OK, false de lo contrario
    */
   public boolean mostrarEditarVentaProductoDialogo(Dato dato) {
       try {
           // cargar el fxml
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/EditarProductoVentaVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();

           // crear el nuevo dialogo
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Editar producto");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(ventaProductoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);

           // entregarle el dato que va a ser editado al controlador
           EditarProductoVentaController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setDato(dato);

           // mostrar el dialogo
           dialogStage.showAndWait();

           return controller.isOkClicked();
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }
   }
   
   /**
    * Abre un dialogo para agregar un nuevo empleado
    * Si el usuario da click a OK guarda los cambios en el objeto empleado y retorna true
    * 
    * @param empleado - el objeto de empleado que se va a agregar
    * @return true si el usuario presiona OK, false de lo contrario
    */
   public boolean mostrarAgregarEmpleadoDialogo(Empleado empleado){
       try{
           // cargar el fxml
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("/view/AgregarEmpleadoVista.fxml"));
           AnchorPane page = (AnchorPane) loader.load();
           
           // crear el nuevo dialogo
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Agregar empleado");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(empleadoVista);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);
           
           // le pasa el empleado al controlador
           AgregarEmpleadoVistaController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setEmpleado(empleado);
           
           // abre el dialogo y espera respuesta del dialogo.
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