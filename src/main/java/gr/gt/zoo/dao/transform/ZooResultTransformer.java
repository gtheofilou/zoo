package gr.gt.zoo.dao.transform;

import gr.gt.zoo.dto.AnimalDto;
import gr.gt.zoo.entity.Animal;
import gr.gt.zoo.entity.Tricks;
import org.hibernate.transform.ResultTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A custom ResultTransformer to facilitate the conversion of
 * the @ManyToMany relation to entities, without extra queries
 */
public class ZooResultTransformer implements ResultTransformer {

    private Map<String, AnimalDto> animalDtoMap = new HashMap<>();

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Animal animal = (Animal) tuple[0];
        Tricks tricks = (Tricks) tuple[1];

        AnimalDto animalDto = animalDtoMap.computeIfAbsent(
                animal.getId(),
                a -> AnimalDto.convertToDto(animal)
        );
        if (tricks != null) {
            animalDto.getTricks().add(tricks);
        }
        return animalDto;
    }

    @Override
    public List transformList(List collection) {
        return new ArrayList<>(animalDtoMap.values());
    }
}
