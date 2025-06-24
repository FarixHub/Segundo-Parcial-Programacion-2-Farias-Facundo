package gui;

import entidades.Medicamento;
import entidades.ProductoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VistaPorVencer extends Stage {

    private ProductoManager manager;

    public VistaPorVencer(ProductoManager manager) {
        this.manager = manager;
        setTitle("Medicamentos por Vencer");

        TableView<Medicamento> table = new TableView<>();

        TableColumn<Medicamento, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreComercial()));

        TableColumn<Medicamento, String> colDosis = new TableColumn<>("Dosis");
        colDosis.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDosis()));

        TableColumn<Medicamento, String> colFecha = new TableColumn<>("Fecha Vencimiento");
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaVencimiento().toString()));

        TableColumn<Medicamento, String> colReceta = new TableColumn<>("Requiere Receta");
        colReceta.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().isRequiereReceta() ? "Sí" : "No"));

        table.getColumns().addAll(colNombre, colDosis, colFecha, colReceta);

        ObservableList<Medicamento> datos = FXCollections.observableArrayList(manager.getMedicamentosPorVencer(30));
        table.setItems(datos);

        Button btnGuardar = new Button("Guardar en Archivo");
        btnGuardar.setOnAction(e -> {
            manager.guardarMedicamentosPorVencer("medicamentos_por_vencer.txt", 30);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Archivo guardado correctamente.");
            alerta.showAndWait();
        });

        VBox root = new VBox(10, table, btnGuardar);
        root.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(root, 600, 450);
        setScene(scene);
    }
}
