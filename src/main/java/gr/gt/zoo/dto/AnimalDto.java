package gr.gt.zoo.dto;

import gr.gt.zoo.entity.Animal;
import gr.gt.zoo.entity.Tricks;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for the presentation layer
 */
@Builder
@Getter
public class AnimalDto {

    private String id;
    private String name;
    private String species;
    private List<Tricks> tricks;

    public static AnimalDto convertToDto(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .species(animal.getSpecies().getName())
                .tricks(new ArrayList<>()).build();
    }

}
