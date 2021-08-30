package gr.gt.zoo.service;

import gr.gt.zoo.dao.AbstractDao;
import gr.gt.zoo.dao.AnimalDao;
import gr.gt.zoo.dao.AnimalTricksAssocDao;
import gr.gt.zoo.dao.TricksDao;
import gr.gt.zoo.dto.AnimalDto;
import gr.gt.zoo.entity.Animal;
import gr.gt.zoo.entity.AnimalTricksAssoc;
import gr.gt.zoo.entity.Tricks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnimalService extends AbstractService<Animal, String> {

    private AnimalDao animalDao;

    @Autowired
    private TricksDao tricksDao;

    @Autowired
    private AnimalTricksAssocDao animalTricksAssocDao;


    @Autowired
    public AnimalService(@Qualifier("AnimalDao") AbstractDao<Animal, String> abstractDao) {
        super(abstractDao);
        this.animalDao = (AnimalDao) abstractDao;
    }


    public List<AnimalDto> fetchAll() {
        return animalDao.fetchAll();
    }

    public Map<String, List<AnimalDto>> groupBySpecies() {
        return animalDao.groupBySpecies();
    }

    public Tricks doTrick(String animalId) {
        AnimalDto animal = animalDao.fetch(animalId);
        if (animal == null) {
            return null;
        } else {
            List<Tricks> tricksList = animal.getTricks();
            return tricksList.get(new Random().nextInt(tricksList.size()));
        }
    }

    public Tricks learnTrick(String animalId) {
        AnimalDto animal = animalDao.fetch(animalId);

        if (animal == null) {
            return null;
        } else {
            List<Tricks> availableTricks = tricksDao.fetchTricksPerSpecies(animal.getSpecies());

            //Create a copy of availableTricks and remove the already known
            List<Tricks> tricksToLearn = availableTricks
                    .stream()
                    .filter(t -> !animal.getTricks().contains(t))
                    .collect(Collectors.toList());

            if (tricksToLearn.isEmpty()) {
                return null;
            } else {
                //Learn the first trick
                Tricks trick = tricksToLearn.get(0);
                animalTricksAssocDao.persist(new AnimalTricksAssoc(animal.getId(), trick.getId()));
                return trick;
            }
        }
    }

}
