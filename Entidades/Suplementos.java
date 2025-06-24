package entidades;

import java.time.LocalDate;

public class Suplementos extends ProductoFarmaceutico {
    private String objetivo;

    public Suplementos(String nombreComercial, String dosis, LocalDate fechaVencimiento, String objetivo) {
        super(nombreComercial, dosis, fechaVencimiento);
        this.objetivo = objetivo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String tipoProducto() {
        return "Suplemento";
    }

    @Override
    public String toString() {
        return super.toString() + " - Objetivo: " + objetivo;
    }
}
