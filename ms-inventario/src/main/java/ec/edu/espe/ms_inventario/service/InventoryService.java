package ec.edu.espe.ms_inventario.service;

import ec.edu.espe.ms_inventario.dto.MsCentralMessageDto;
import ec.edu.espe.ms_inventario.entity.Supply;
import ec.edu.espe.ms_inventario.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void updateEntity(MsCentralMessageDto dto) {
        try {
            List<Supply> insumosNecesarios = inventoryRepository.findByCategoria(dto.getProducto());

            if (insumosNecesarios.isEmpty()) {
                throw new NoSuchElementException("No se encontraron insumos para la categoría: " + dto.getProducto());
            }

            for(Supply insumo : insumosNecesarios) {
                double insumo_necesario_usado = 0.0;
                if(insumo.getNombreInsumo().toLowerCase().contains("semilla")){
                    insumo_necesario_usado = dto.getToneladas() * 5;
                } else if (insumo.getNombreInsumo().toLowerCase().contains("fertilizante")) {
                    insumo_necesario_usado = dto.getToneladas() * 2;
                } else{
                    throw new NoSuchElementException("Insumo no contemprado en la fórmula");
                }

                insumo.setStock(insumo.getStock() - insumo_necesario_usado);
                inventoryRepository.save(insumo);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
