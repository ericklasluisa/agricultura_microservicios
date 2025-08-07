package ec.edu.espe.ms_inventario.repository;

import ec.edu.espe.ms_inventario.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Supply, UUID> {
    List<Supply> findByCategoria(String categoria);
}
