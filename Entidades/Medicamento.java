package entidades;

import java.time.LocalDate;

public class Medicamento extends ProductoFarmaceutico {
    private boolean requiereReceta;

    public Medicamento(String nombreComercial, String dosis, LocalDate fechaVencimiento, boolean requiereReceta) {
        super(nombreComercial, dosis, fechaVencimiento);
        this.requiereReceta = requiereReceta;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    @Override
    public String tipoProducto() {
        return "Medicamento";
    }

    @Override
    public String toString() {
        return super.toString() + " - Receta: " + (requiereReceta ? "Sí" : "No");
    }
}
