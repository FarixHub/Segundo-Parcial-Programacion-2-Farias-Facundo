package gui;

import entidades.ProductoFarmaceutico;
import entidades.ProductoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaListaProductos extends Stage {

    private ProductoManager manager;

    public VistaListaProductos(ProductoManager manager) {
        this.manager = manager;
        setTitle("Lista de Productos");

        TableView<ProductoFarmaceutico> table = new TableView<>();

        TableColumn<ProductoFarmaceutico, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreComercial()));

        TableColumn<ProductoFarmaceutico, String> colDosis = new TableColumn<>("Dosis");
        colDosis.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDosis()));

        TableColumn<ProductoFarmaceutico, String> colFecha = new TableColumn<>("Fecha Vencimiento");
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaVencimiento().toString()));

        TableColumn<ProductoFarmaceutico, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().tipoProducto()));

        table.getColumns().addAll(colNombre, colDosis, colFecha, colTipo);

        ObservableList<ProductoFarmaceutico> datos = FXCollections.observableArrayList(manager.getProductos());
        table.setItems(datos);

        // Botones eliminar y modificar pueden agregarse después

        VBox root = new VBox(table);
        root.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
    }
}
