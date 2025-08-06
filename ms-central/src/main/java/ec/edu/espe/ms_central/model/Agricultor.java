package ec.edu.espe.ms_central.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "agricultor")
public class Agricultor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long agricultor_id;

  private String nombre;
  private String finca;
  private String ubicacion;
  private String correo;

  @Column(name = "fecha_registro", updatable = false)
  private LocalDateTime fecha_registro;

  @OneToMany(mappedBy = "agricultor", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Cosecha> cosechas;

  @PrePersist
  protected void onCreate() {
    fecha_registro = LocalDateTime.now();
  }
}
