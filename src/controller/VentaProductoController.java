package controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import model.Cliente;
import model.facturacion.Cola;
import model.facturacion.NodoC;
import model.venta.Dato;
import model.venta.Venta;

/**
 * Clase que se encarga de mantener la venta de productos
 * 
 * @author Elena
 */
public class VentaProductoController {
    // tabla y columnas
    @FXML
    private TableView<Dato> ventasTable;
    @FXML
    private TableColumn<Dato, String> productoColumn;
    @FXML
    private TableColumn<Dato, Integer> cantidadColumn;
    @FXML 
    private TableColumn<Dato, Double> precioColumn;
    private Cliente cliente;

    private MainApp mainApp;
    
    /**
     * Constructor por defecto. 
     * Llamado antes de que se llame el método de inicializar.
     */
    public VentaProductoController() {
    }
    
    /**
     * Metodo que inicializa la clase de controlador. Es llamado despues de que el fxml es cargado
     * Inicializa la tabla con la información de nombre del producto, la cantidad que lleva y el precio por unidad
     */
    @FXML
    private void initialize() {
        productoColumn.setCellValueFactory(cellData -> cellData.getValue().getDato().getDato().nombreProperty());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().getDato().getDato().precioVentaProperty().asObject());
    }
    
    /**
     * Llamado por el main para tener una referencia hacia él mismo
     * Inicializa la tabla de ventas con la información almacenada en el arreglo de ventas en Main.
     * @param mainApp - recibe el mainApp de mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        ventasTable.setItems(mainApp.getVentasDato());
    }
        
    /**
     * Método para manejar el botón de volver al menú.
     * Vuelve al menú.
     */
    @FXML
    private void handleMenuBtn(){
       mainApp.mostrarInicio();   
    }
    
    /**
     * Método que permite ver el total de compra hasta el momento con los productos en carrito de compras.
     * Muestra el total si hay productos; error si no hay productos aún. 
     */
    @FXML
    private void handleVerTotal(){
        double total = 0;
        int tableSize = ventasTable.getItems().size();
        if(tableSize == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Aún no hay datos en venta");
            alert.showAndWait();
        }
        else{
            for(int i=0;i<tableSize;i++){
                total+=ventasTable.getItems().get(i).getPrecioFinal();
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Total a pagar hasta el momento: ₡"+total);
            alert.showAndWait();
        }
    }
    
    /**
     * Método que permite agregar productos al carrito.
     * Abre un dialogo de agregar venta, revisa si hay suficientes y muestra error si no
     * Si hay suficientes los agrega al arreglo de venta.
     */
    @FXML
    private void handleAgregarBtn(){
       Dato dato = new Dato();
       boolean okClicked = mainApp.mostrarAgregarVentaDialog(dato);
       if (okClicked){
           // revisar si hay suficientes
           if(!Venta.haySuficientes(dato)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setTitle("Error en agregar");
                alert.setHeaderText("Error al agregar cantidad");
                alert.setContentText("La cantidad indicada es mayor a la que hay en inventario. Por favor agregue más a "
                        + "inventario o corríja la cantidad.");
                alert.showAndWait();
            }else{
                mainApp.getVentasDato().add(dato);
                Venta.nuevaVenta(dato);
            }
       }
    }
    
    /**
     * Método que se encarga de la parte de facturar productos. 
     * Primero revisará si la tabla no está vacia. Si no, crea un nuevo dialogo y le pide al administrador
     * el nombre del cliente para realizar la factura.
     * 
     * Suma todos los valores de los productos y le agrega el impuesto de venta. Guarda también esta información
     * a la factura del cliente.
     * 
     * pide al administrador confirmar si el cliente paga con tarjeta o con efectivo. De pagar con efectivo
     * le pedirá al cliente el total de billetes y le devolverá la cantidad que debe de dar de vuelto.
     */
    @FXML
    private void handleFacturarBtn(){
        try{
            if(!ventasTable.getItems().isEmpty()){
                Cliente nuevoCliente = new Cliente();
                TextInputDialog dialog = new TextInputDialog("");
                Alert alert = new Alert(AlertType.INFORMATION);
                dialog.setTitle("Nombre de factura");
                dialog.setHeaderText("¿Factura a nombre de quién?");
                Optional<String> nombreFactura = dialog.showAndWait();
                nuevoCliente.setNombre(nombreFactura.get());
                
                double total = 0;
                int tableSize = ventasTable.getItems().size();
                for(int i=0;i<tableSize;i++){
                        total+=ventasTable.getItems().get(i).getPrecioFinal();
                }
                final double IMPUESTO = 0.13;
                double precioFinal = total + (total*IMPUESTO);
                
                nuevoCliente.setTotal(precioFinal);
                nuevoCliente.setFechaCompra(LocalDateTime.now());
                mainApp.getFacturaDatos().add(nuevoCliente);
                
                NodoC cliente = new NodoC(nuevoCliente);
                Cola.meteFacturas(cliente);
                
                int pagaCon = 0;
                try{
                    dialog = new TextInputDialog("");
                    dialog.setTitle("Venta terminada satisfactoriamente");
                    dialog.setHeaderText("Factura a nombre de: "+nombreFactura.get()+
                            "\n\nMonto a pagar: ₡"+precioFinal
                            +"\n\n¿Con cuánto dinero paga el cliente? Presione 0 si el cliente paga con tarjeta.\n");
                    Optional<String> result = dialog.showAndWait();
                    pagaCon = Integer.parseInt(result.get());
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }

                while(pagaCon<precioFinal && pagaCon!=0){
                    dialog = new TextInputDialog("");
                    dialog.setTitle("Venta terminada satisfactoriamente");
                    dialog.setHeaderText("Factura a nombre de: "+nombreFactura.get()+
                            "\n\nMonto a pagar: ₡"+precioFinal
                            +"\n\nLa cantidad de dinero con la que paga el cliente debe de ser mayor al monto "
                                    + "que paga, por favor verifique los datos. Presione 0 si el cliente paga con tarjeta.\n");
                    Optional<String> result = dialog.showAndWait();
                    try{
                        pagaCon = Integer.parseInt(result.get());
                    }catch(NumberFormatException e){
                        e.printStackTrace();
                    }
                }

                if(pagaCon!=0){
                    alert.setTitle("Vuelto");
                    alert.setHeaderText("Cambio a entregar a cliente: "+(pagaCon-precioFinal));
                    alert.showAndWait();
                }    

                Venta.eliminarVenta();
                try{
                    ventasTable.getItems().clear();
                }catch(UnsupportedOperationException e){
                    e.printStackTrace();
                }
            }else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Total hasta el momento");
                alert.setHeaderText(null);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setContentText("Aún no hay datos en venta");
                alert.showAndWait();
            }
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Manejar el botón de editar productos en venta. Esto lo logra por medio del dialogo que abre
     * hacia el controlador de editar productos en venta.
     */
    @FXML
    private void handleEditarBtn(){
       Dato productoSeleccionado = ventasTable.getSelectionModel().getSelectedItem();
       if (productoSeleccionado != null) {
           boolean okClicked = mainApp.mostrarEditarVentaProductoDialogo(productoSeleccionado);
           if (okClicked) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cambio realizado exitosamente");
                alert.setHeaderText(null);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setContentText("El cambio de cantidad concluyo de manera exitosa.");
                alert.showAndWait();
            }
       } else {
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(mainApp.getProductoVista());
           alert.setTitle("No seleccionó nada");
           alert.setHeaderText("Ningun producto seleccionado");
           alert.setContentText("Por favor seleccione el producto a editar de la tabla");
           alert.showAndWait();
       }       
    }
    
    /**
     * Botón para eliminar todos los elementos del carrito. Devuelve todos los productos a inventario.
     * Al finalizar limpia la tabla y la pila.
     */
    @FXML
    private void handleEliminarTodosBtn(){
        if(!ventasTable.getItems().isEmpty()){
            int tableSize = ventasTable.getItems().size();
            for(int i=0;i<tableSize;i++){
                Venta.restaurarInventario(ventasTable.getItems().get(i).getDato(), ventasTable.getItems().get(i)
                        .getDato().getDato().getCodigo());
            }
            Venta.eliminarVenta();
            try{
                ventasTable.getItems().clear();
            }catch(UnsupportedOperationException e){
                e.printStackTrace();
            }
            if(ventasTable.getItems().isEmpty()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Eliminados");
                alert.setHeaderText(null);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setContentText("Productos eliminados de manera exitosa.");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Aún no hay datos en venta");
            alert.showAndWait();
        }
    }
    
    /**
     * Método que permite eliminar un elemento específico de la tabla cuando se selecciona.
     * Devuelve su cantidad al inventario y lo quita de la pila y tabla.
     */
    @FXML
    private void handleEliminarBtn(){
        if(!ventasTable.getItems().isEmpty()){
            Dato productoSeleccionado = ventasTable.getSelectionModel().getSelectedItem();
            if(productoSeleccionado!=null){
                Venta.restaurarInventario(productoSeleccionado.getDato(), productoSeleccionado.getDato().getDato().getCodigo());
                ventasTable.getItems().remove(productoSeleccionado);
                Venta.eliminarDato(productoSeleccionado);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Eliminado");
                alert.setHeaderText(null);
                alert.initOwner(mainApp.getVentaProductoVista());
                alert.setContentText("Producto eliminado de manera exitosa.");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Total hasta el momento");
            alert.setHeaderText(null);
            alert.initOwner(mainApp.getVentaProductoVista());
            alert.setContentText("Aún no hay datos en venta");
            alert.showAndWait();
        }
    }
   
}
