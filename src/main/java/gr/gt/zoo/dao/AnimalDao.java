package gr.gt.zoo.dao;

import gr.gt.zoo.dao.transform.ZooResultTransformer;
import gr.gt.zoo.dto.AnimalDto;
import gr.gt.zoo.entity.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("AnimalDao")
@SuppressWarnings("deprecation")
public class AnimalDao extends AbstractDao<Animal, String> {

    public List<AnimalDto> fetchAll() {
        return entityManager.createQuery(Animal.FETCH_ALL)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new ZooResultTransformer())
                .getResultList();
    }

    public Map<String, List<AnimalDto>> groupBySpecies() {
        return fetchAll()
                .stream()
                .collect(Collectors.groupingBy(AnimalDto::getSpecies));
    }

    public AnimalDto fetch(String animalId) {
        List<AnimalDto> res = entityManager.createQuery(Animal.FETCH)
                .setParameter("animalId", animalId)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new ZooResultTransformer())
                .getResultList();

        return res.isEmpty() ? null : res.get(0);
    }
}
