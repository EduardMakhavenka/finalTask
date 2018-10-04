package by.makhavenka.task.dao.builder;

import by.makhavenka.task.dao.DaoConstant;
import by.makhavenka.task.entity.User;
import by.makhavenka.task.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User build(ResultSet set) throws DAOException{
        User user ;
        try {
                user = new User();
                user.setId(set.getInt(DaoConstant.ID));
                user.setName(set.getString(DaoConstant.NAME));
                user.setSurname(set.getString(DaoConstant.SURNAME));
                user.setLogin(set.getString(DaoConstant.LOGIN));
                user.setMail(set.getString(DaoConstant.MAIL));
                user.setPassword(set.getString(DaoConstant.PASSWORD));
                user.setRating(set.getInt(DaoConstant.RATING));
                user.setBanned(set.getInt(DaoConstant.BANNED));
                user.setRole(set.getString(DaoConstant.ROLE));
                user.setPhoto(set.getString(DaoConstant.PHOTO));
                user.setPhone(set.getString(DaoConstant.PHONE));
                user.setSex(set.getString(DaoConstant.SEX));
                user.setAge(set.getInt(DaoConstant.AGE));
                user.setFavotiteHero(set.getString(DaoConstant.FAVORITE_HERO));
                user.setNickName(set.getString(DaoConstant.NICKNAME));
        } catch (SQLException e) {
            throw new DAOException("dao user builder error",e);
        }
        return user;
    }

    public PreparedStatement fillStatement(User user,PreparedStatement statement) throws DAOException{
        try {
            statement.setString(1,user.getName());
            statement.setString(2,user.getSurname());
            statement.setString(3,user.getLogin());
            statement.setString(4,user.getMail());
            statement.setString(5,user.getPassword());
            statement.setInt(6,user.getRating());
            statement.setInt(7,user.getBanned());
            statement.setString(8,user.getRole());
            statement.setString(9,user.getPhoto());
            statement.setString(10,user.getPhone());
            statement.setString(11,user.getSex());
            statement.setInt(12,user.getAge());
            statement.setString(13,user.getFavotiteHero());
            statement.setString(14,user.getNickName());
        } catch (SQLException e) {
            throw new DAOException("dao user bulder fill statement error",e);
        }
        return statement;
    }
}
