package by.makhavenka.task.dao.builder;

import by.makhavenka.task.dao.DaoConstant;
import by.makhavenka.task.entity.Hero;
import by.makhavenka.task.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HeroBuilder {

    public Hero buildHero(ResultSet set) throws DAOException{
        Hero hero = new Hero();
        try {
            hero.setId(set.getInt(DaoConstant.ID));
            hero.setName(set.getString(DaoConstant.NAME));
            hero.setRating(set.getInt(DaoConstant.RATING));
            hero.setDescription(set.getString(DaoConstant.DESCRIPTION));
            hero.setType(set.getString(DaoConstant.HERO_TYPE));
            hero.setDeleted(set.getInt(DaoConstant.DELETED));
        } catch (SQLException e) {
            throw  new DAOException("dao hero builder error ",e);
        }

        return hero;
    }

    public PreparedStatement fillStatement(Hero hero, PreparedStatement statement) throws DAOException{
        try {
            statement.setString(1, hero.getName());
            statement.setInt(2, hero.getRating());
            statement.setString(3,hero.getDescription());
            statement.setString(4,hero.getType());
            statement.setInt(5,hero.getDeleted());
        } catch (SQLException e) {
            throw new DAOException("dao hero bulder fill statement error",e);
        }
        return statement;
    }
}
