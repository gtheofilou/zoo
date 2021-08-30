package gr.gt.zoo.dao;

import gr.gt.zoo.entity.Species;
import org.springframework.stereotype.Repository;

@Repository("SpeciesDao")
public class SpeciesDao extends AbstractDao<Species, String> {

}
