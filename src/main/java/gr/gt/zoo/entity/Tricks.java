package gr.gt.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tricks {

    public static final String FETCH_TRICKS_PER_SPECIES = "select distinct t from Animal a " +
            "left join AnimalTricksAssoc assoc on a.id = assoc.animalId " +
            "left join Species s on s.id = a.speciesId " +
            "left join Tricks t on t.id = assoc.trickId " +
            "where s.name = :species";

    @Id
    private String id;

    @NotNull
    private String name;

}

