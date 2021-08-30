package gr.gt.zoo.controller;


import gr.gt.zoo.dto.AnimalDto;
import gr.gt.zoo.entity.Tricks;
import gr.gt.zoo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    /**
     * Returns all the animals in the zoo
     *
     * @return All animals in JSON array
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<AnimalDto> animals = animalService.fetchAll();
        return ResponseEntity.ok().body(animals);
    }

    /**
     * Returns all the animals grouped by species
     *
     * @return JSON array
     */
    @GetMapping(value = "/species", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> groupBySpecies() {
        Map<String, List<AnimalDto>> groupBySpecies = animalService.groupBySpecies();
        return ResponseEntity.ok().body(groupBySpecies);
    }

    /**
     * This endpoint accepts the Id of an animal and does a trick randomly
     *
     * @param animalId The id of the animal in the zoo
     * @return The trick that the animal did
     */
    @GetMapping(value = "/{animalId}/doTrick", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doTrick(@PathVariable String animalId) {
        Tricks trick = animalService.doTrick(animalId);
        String trickName = trick != null ? trick.getName() : "";
        return ResponseEntity.ok().body(Map.of("trick", trickName));
    }

    /**
     * This endpoint accepts the Id of an animal and learns a trick
     *
     * @param animalId The id of the animal in the zoo
     * @return The trick that the animal learnt
     */
    @GetMapping(value = "/{animalId}/learnTrick", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> learnTrick(@PathVariable String animalId) {
        Tricks trick = animalService.learnTrick(animalId);
        String trickName = trick != null ? trick.getName() : "";
        return ResponseEntity.ok().body(Map.of("trick", trickName));
    }

}