package gr.gt.zoo.util;

import gr.gt.zoo.dao.extra.RidGenerator;
import gr.gt.zoo.dto.AnimalDto;
import gr.gt.zoo.entity.Animal;
import gr.gt.zoo.entity.AnimalTricksAssoc;
import gr.gt.zoo.entity.Species;
import gr.gt.zoo.entity.Tricks;
import gr.gt.zoo.service.AnimalService;
import gr.gt.zoo.service.AnimalTricksAssocService;
import gr.gt.zoo.service.SpeciesService;
import gr.gt.zoo.service.TricksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class to initialize database
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private RidGenerator ridGenerator;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private TricksService tricksService;

    @Autowired
    private AnimalTricksAssocService animalTricksAssocService;

    @Override
    public void run(String... args) throws Exception {

        //Persist Species
        speciesService.persist(new Species(ridGenerator.nextRid(), "dog"));
        speciesService.persist(new Species(ridGenerator.nextRid(), "cat"));
        speciesService.persist(new Species(ridGenerator.nextRid(), "fish"));
        speciesService.persist(new Species(ridGenerator.nextRid(), "horse"));

        //Retrieve Species to get primary keys
        List<Species> speciesList = speciesService.findAll();
        int size = speciesList.size();


        //Persist Animals
        for (int i = 0; i < 1000; i++) {
            animalService.persist(new Animal(ridGenerator.nextRid(), "Animal_" + i, speciesList.get(i % size).getId()));
        }
        //Retrieve Animals to get primary keys
        List<AnimalDto> animalList = animalService.fetchAll();

        tricksService.persist(new Tricks(ridGenerator.nextRid(), "walksOnLaptop"));
        tricksService.persist(new Tricks(ridGenerator.nextRid(), "jumps"));
        tricksService.persist(new Tricks(ridGenerator.nextRid(), "rollsOver"));
        tricksService.persist(new Tricks(ridGenerator.nextRid(), "barks"));

        //Add extra tricks to learn
        tricksService.persist(new Tricks(ridGenerator.nextRid(), "extra1"));
        tricksService.persist(new Tricks(ridGenerator.nextRid(), "extra2"));

        Map<String, Tricks> tricksMap = tricksService.findAll().stream().collect(Collectors.toMap(Tricks::getName, Function.identity()));
        for (AnimalDto animal : animalList) {
            switch (animal.getSpecies()) {
                case "dog":
                    animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("jumps").getId()));
                    animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("rollsOver").getId()));
                    animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("barks").getId()));
                    break;
                case "cat":
                    animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("walksOnLaptop").getId()));
                    break;
                case "horse":
                    animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("jumps").getId()));
                    break;
                default:
                    // do nothing
            }
        }

        AnimalDto animal = animalList.stream().filter(a -> "dog".equals(a.getSpecies())).findFirst().get();
        animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("extra1").getId()));
        animalTricksAssocService.persist(new AnimalTricksAssoc(animal.getId(), tricksMap.get("extra2").getId()));

    }
}
