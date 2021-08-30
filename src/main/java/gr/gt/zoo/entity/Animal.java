package gr.gt.zoo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Animal {

    public static final String FETCH_ALL = "select a, t, s from Animal a " +
            "left join AnimalTricksAssoc assoc on a.id = assoc.animalId " +
            "left join Species s on s.id = a.speciesId " +
            "left join Tricks t on t.id = assoc.trickId ";

    public static final String FETCH = "select a,t,s from Animal a " +
            "left join AnimalTricksAssoc assoc on a.id = assoc.animalId " +
            "left join Species s on s.id = a.speciesId " +
            "left join Tricks t on t.id = assoc.trickId " +
            "where a.id = :animalId";

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String speciesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciesId", referencedColumnName = "id", insertable = false, updatable = false)
    private Species species;

    public Animal(String id, String name, String speciesId) {
        this.id = id;
        this.name = name;
        this.speciesId = speciesId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciesId=" + speciesId +
                ", species=" + species +
                '}';
    }
}
