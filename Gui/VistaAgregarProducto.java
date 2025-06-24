package gui;

import entidades.FechaVencidaException;
import entidades.Medicamento;
import entidades.ProductoFarmaceutico;
import entidades.ProductoManager;
import entidades.Suplementos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class VistaAgregarProducto extends Stage {

    private ProductoManager manager;

    public VistaAgregarProducto(ProductoManager manager) {
        this.manager = manager;
        setTitle("Agregar Producto");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblNombre = new Label("Nombre:");
        TextField tfNombre = new TextField();
        Label lblDosis = new Label("Dosis:");
        TextField tfDosis = new TextField();
        Label lblFecha = new Label("Fecha Vencimiento:");
        DatePicker dpFecha = new DatePicker(LocalDate.now());

        ToggleGroup tipoGroup = new ToggleGroup();
        RadioButton rbMedicamento = new RadioButton("Medicamento");
        rbMedicamento.setToggleGroup(tipoGroup);
        rbMedicamento.setSelected(true);
        RadioButton rbSuplemento = new RadioButton("Suplemento");
        rbSuplemento.setToggleGroup(tipoGroup);

        Label lblReceta = new Label("Requiere receta:");
        CheckBox cbReceta = new CheckBox();

        Label lblObjetivo = new Label("Objetivo:");
        TextField tfObjetivo = new TextField();
        tfObjetivo.setDisable(true);

        // Cambiar campos según tipo seleccionado
        tipoGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == rbMedicamento) {
                cbReceta.setDisable(false);
                lblReceta.setDisable(false);
                tfObjetivo.setDisable(true);
                lblObjetivo.setDisable(true);
            } else {
                cbReceta.setDisable(true);
                lblReceta.setDisable(true);
                tfObjetivo.setDisable(false);
                lblObjetivo.setDisable(false);
            }
        });

        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            try {
                String nombre = tfNombre.getText().trim();
                String dosis = tfDosis.getText().trim();
                LocalDate fecha = dpFecha.getValue();

                if (nombre.isEmpty() || dosis.isEmpty() || fecha == null) {
                    mostrarAlerta("Error", "Todos los campos obligatorios deben completarse.");
                    return;
                }

                ProductoFarmaceutico p;
                if (rbMedicamento.isSelected()) {
                    p = new Medicamento(nombre, dosis, fecha, cbReceta.isSelected());
                } else {
                    String objetivo = tfObjetivo.getText().trim();
                    if (objetivo.isEmpty()) {
                        mostrarAlerta("Error", "Debe ingresar el objetivo del suplemento.");
                        return;
                    }
                    p = new Suplementos(nombre, dosis, fecha, objetivo);
                }

                manager.agregarProducto(p);
                mostrarAlerta("Éxito", "Producto agregado correctamente.");
                this.close();
            } catch (FechaVencidaException ex) {
                mostrarAlerta("Error", ex.getMessage());
            } catch (Exception ex) {
                mostrarAlerta("Error", "Ocurrió un error inesperado.");
                ex.printStackTrace();
            }
        });

        grid.add(lblNombre, 0, 0);
        grid.add(tfNombre, 1, 0);
        grid.add(lblDosis, 0, 1);
        grid.add(tfDosis, 1, 1);
        grid.add(lblFecha, 0, 2);
        grid.add(dpFecha, 1, 2);
        grid.add(rbMedicamento, 0, 3);
        grid.add(rbSuplemento, 1, 3);
        grid.add(lblReceta, 0, 4);
        grid.add(cbReceta, 1, 4);
        grid.add(lblObjetivo, 0, 5);
        grid.add(tfObjetivo, 1, 5);
        grid.add(btnAgregar, 1, 6);

        Scene scene = new Scene(grid, 350, 300);
        setScene(scene);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
