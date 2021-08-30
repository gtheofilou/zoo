package gr.gt.zoo.dao;

import gr.gt.zoo.entity.Tricks;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TricksDao")
public class TricksDao extends AbstractDao<Tricks, String> {

    public List<Tricks> fetchTricksPerSpecies(String species) {
        List<Tricks> resultList = entityManager.createQuery(Tricks.FETCH_TRICKS_PER_SPECIES)
                .setParameter("species", species)
                .getResultList();

        return resultList;
    }

}
