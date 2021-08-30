package gr.gt.zoo.service;

import gr.gt.zoo.dao.AbstractDao;
import gr.gt.zoo.dao.SpeciesDao;
import gr.gt.zoo.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SpeciesService extends AbstractService<Species, String> {

    private SpeciesDao speciesDao;

    @Autowired
    public SpeciesService(@Qualifier("SpeciesDao") AbstractDao<Species, String> abstractDao) {
        super(abstractDao);
        this.speciesDao = (SpeciesDao) abstractDao;
    }

}
