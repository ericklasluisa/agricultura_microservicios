package ec.edu.espe.ms_central.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.edu.espe.ms_central.model.Cosecha;

@Repository
public interface CosechaRepository extends JpaRepository<Cosecha, Long> {

  @Query("SELECT c FROM cosecha c WHERE c.agricultor.agricultor_id = :agricultorId")
  List<Cosecha> findByAgricultorId(@Param("agricultorId") Long agricultorId);

}
