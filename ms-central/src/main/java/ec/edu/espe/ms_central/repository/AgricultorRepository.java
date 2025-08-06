package ec.edu.espe.ms_central.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.ms_central.model.Agricultor;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {

}
