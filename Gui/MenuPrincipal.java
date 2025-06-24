package gui;

import entidades.ProductoManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal {

    private ProductoManager manager;

    public MenuPrincipal() {
        manager = new ProductoManager();
    }

    public void mostrar(Stage primaryStage) {
        Button btnListar = new Button("Listar Productos");
        btnListar.setOnAction(e -> {
            VistaListaProductos lista = new VistaListaProductos(manager);
            lista.show();
        });

        Button btnAgregar = new Button("Agregar Producto");
        btnAgregar.setOnAction(e -> {
            VistaAgregarProducto agregar = new VistaAgregarProducto(manager);
            agregar.show();
        });

        Button btnPorVencer = new Button("Medicamentos por Vencer");
        btnPorVencer.setOnAction(e -> {
            VistaPorVencer vistaPV = new VistaPorVencer(manager);
            vistaPV.show();
        });

        VBox root = new VBox(10, btnListar, btnAgregar, btnPorVencer);
        root.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Farmacia Don Alberto - Menú Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
