
package app.block5crudvalidation.Campeonato.Domain.Entities;

import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campeonato")
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campeonatoId;

    private String nombre;
    private boolean formato;
    private Date fechaInicio;
    private String ganador;
    private String foto;
    private String pais;

    @OneToMany(mappedBy = "campeonato")
    private Set<CampeonatoEquipo> campeonatoEquipos;
}
