package gr.gt.zoo.service;

import gr.gt.zoo.dao.AbstractDao;
import gr.gt.zoo.dao.TricksDao;
import gr.gt.zoo.entity.Tricks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TricksService extends AbstractService<Tricks, String> {

    private TricksDao tricksDao;

    @Autowired
    public TricksService(@Qualifier("TricksDao") AbstractDao<Tricks, String> abstractDao) {
        super(abstractDao);
        this.tricksDao = (TricksDao) abstractDao;
    }

}
