package app.block5crudvalidation.CampeonatoEquipo.Domain.Entities;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campeonato_equipo")
@IdClass(CampeonatoEquipoKey.class)
public class CampeonatoEquipo {


    @Id
    @ManyToOne
    @JoinColumn(name = "campeonato_id", referencedColumnName = "campeonatoId")
    private Campeonato campeonato;

    @Id
    @ManyToOne
    @JoinColumn(name = "equipo_id", referencedColumnName = "equipoId")
    private Equipo equipo;

    private String posicion;
}
