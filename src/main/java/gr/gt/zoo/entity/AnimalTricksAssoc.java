package gr.gt.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table
@IdClass(AnimalTricksAssocId.class)
@NoArgsConstructor
@AllArgsConstructor
public class AnimalTricksAssoc {

    @Id
    private String animalId;

    @Id
    private String trickId;

}
