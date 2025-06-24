package entidades;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class ProductoFarmaceutico implements Serializable {
    private String nombreComercial;
    private String dosis;
    private LocalDate fechaVencimiento;

    public ProductoFarmaceutico(String nombreComercial, String dosis, LocalDate fechaVencimiento) {
        this.nombreComercial = nombreComercial;
        this.dosis = dosis;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public abstract String tipoProducto();

    @Override
    public String toString() {
        return nombreComercial + " - " + dosis + " - Vence: " + fechaVencimiento;
    }
}
