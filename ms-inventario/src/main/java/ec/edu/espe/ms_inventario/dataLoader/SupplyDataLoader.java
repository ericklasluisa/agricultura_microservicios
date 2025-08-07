package ec.edu.espe.ms_inventario.dataLoader;

import ec.edu.espe.ms_inventario.entity.Supply;
import ec.edu.espe.ms_inventario.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SupplyDataLoader implements CommandLineRunner {
    private final InventoryRepository insumoRepository;

    public SupplyDataLoader(InventoryRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    @Override
    public void run(String... args) {
        if (insumoRepository.count() == 0) {
            List<Supply> insumos = List.of(
                    crearInsumo("Semilla Arroz L-23", 100.0, "kg", "Arroz Oro"),
                    crearInsumo("Semilla Arroz K-33", 500.0, "kg", "Arroz Integral"),
                    crearInsumo("Semilla Arroz S-56", 200.0, "kg", "Arroz Arborio"),
                    crearInsumo("Fertilizante N-PK", 300.0, "kg", "Arroz Oro")
            );

            insumoRepository.saveAll(insumos);
            System.out.println("📦 Insumos cargados en la base de datos.");
        } else {
            System.out.println("📦 Insumos ya estaban cargados.");
        }
    }

    private Supply crearInsumo(String nombre, double stock, String unidad, String categoria) {
        Supply insumo = new Supply();
        insumo.setNombreInsumo(nombre);
        insumo.setStock(stock);
        insumo.setUnidadMedida(unidad);
        insumo.setCategoria(categoria);
        insumo.setUltimaActualizacion(LocalDateTime.now());
        return insumo;
    }
}
