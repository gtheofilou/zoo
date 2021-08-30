package gr.gt.zoo.service;

import gr.gt.zoo.dao.AbstractDao;
import gr.gt.zoo.dao.AnimalTricksAssocDao;
import gr.gt.zoo.entity.AnimalTricksAssoc;
import gr.gt.zoo.entity.AnimalTricksAssocId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnimalTricksAssocService extends AbstractService<AnimalTricksAssoc, AnimalTricksAssocId> {

    private AnimalTricksAssocDao animalTricksAssocDao;

    @Autowired
    public AnimalTricksAssocService(@Qualifier("AnimalTricksAssocDao") AbstractDao<AnimalTricksAssoc, AnimalTricksAssocId> abstractDao) {
        super(abstractDao);
        this.animalTricksAssocDao = (AnimalTricksAssocDao) abstractDao;
    }
}
