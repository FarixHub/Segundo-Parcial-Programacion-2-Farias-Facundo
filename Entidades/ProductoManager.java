package entidades;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoManager {
    private List<ProductoFarmaceutico> productos;
    private final String archivoDatos = "productos.dat";

    public ProductoManager() {
        productos = new ArrayList<>();
        cargarDatos();
    }

    public void agregarProducto(ProductoFarmaceutico p) throws FechaVencidaException {
        if (p.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new FechaVencidaException("La fecha de vencimiento no puede ser anterior a la fecha actual.");
        }
        productos.add(p);
        guardarDatos();
    }

    public void modificarProducto(int index, ProductoFarmaceutico p) throws FechaVencidaException {
        if (p.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new FechaVencidaException("La fecha de vencimiento no puede ser anterior a la fecha actual.");
        }
        productos.set(index, p);
        guardarDatos();
    }

    public void eliminarProducto(int index) {
        productos.remove(index);
        guardarDatos();
    }

    public List<ProductoFarmaceutico> getProductos() {
        return productos;
    }

    public List<Medicamento> getMedicamentosPorVencer(int dias) {
        LocalDate limite = LocalDate.now().plusDays(dias);
        return productos.stream()
                .filter(p -> p instanceof Medicamento)
                .map(p -> (Medicamento) p)
                .filter(m -> !m.getFechaVencimiento().isAfter(limite))
                .collect(Collectors.toList());
    }

    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDatos))) {
            productos = (List<ProductoFarmaceutico>) ois.readObject();
        } catch (FileNotFoundException e) {
            // No hay archivo, empieza vacío
            productos = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            productos = new ArrayList<>();
        }
    }

    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDatos))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarMedicamentosPorVencer(String archivo, int dias) {
        List<Medicamento> meds = getMedicamentosPorVencer(dias);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Medicamento m : meds) {
                bw.write(m.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
