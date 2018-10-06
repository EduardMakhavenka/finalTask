package by.makhavenka.task.service.impl;

import by.makhavenka.task.dao.Dao;
import by.makhavenka.task.dao.impl.HeroDaoImpl;
import by.makhavenka.task.dao.impl.ImageDaoImpl;
import by.makhavenka.task.entity.Hero;
import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.service.DownloadeService;

public class DownlodeServiceImpl implements DownloadeService {
    /**
     * method downlodes image title
     * use ImageDaoImpl class and HeroDaoImpl,
     * methods: findCommentById,addImageHero
     * @param id
     * @return String
     * @throws ServiceException
     */
    @Override
    public String downlodeHeroImage(int id) throws ServiceException {
        ImageDaoImpl dao = new ImageDaoImpl();
        Dao heroDao = new HeroDaoImpl();
        Hero hero;
        try {
            hero=(Hero)heroDao.findById(id);
            dao.addImageHero(hero.getName().toLowerCase(),id);
        } catch (DAOException e) {
            throw new ServiceException("downlodehero hero  image error",e);
        }
        return hero.getName();
    }

}
