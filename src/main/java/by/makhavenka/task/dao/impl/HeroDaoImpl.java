package by.makhavenka.task.dao.impl;

import by.makhavenka.task.dao.HeroDao;
import by.makhavenka.task.dao.builder.CommentBuilder;
import by.makhavenka.task.dao.builder.HeroBuilder;
import by.makhavenka.task.dao.DaoConstant;
import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.entity.Entity;
import by.makhavenka.task.entity.Hero;
import by.makhavenka.task.exception.DAOException;
import by.makhavenka.task.poolconnection.ConnectionProxy;
import by.makhavenka.task.poolconnection.Pool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroDaoImpl implements HeroDao {

    private final static String CREATE_HERO = " INSERT INTO finaldb20.hero (name,rating,description,type,deleted) " +
            " VALUES (?,?,?,?,?)";

    private final static String SELECT_ALL = "SELECT id,name,rating,description,type,deleted FROM finaldb20.hero";

    private final static String SELECT_HERO_BY_ID= "SELECT id,name,rating,description,type,deleted FROM finaldb20.hero " +
            "WHERE id=?";

    private final static String SEARCH_BY_NAME= "SELECT  id,name,rating,description,type,deleted " +
            "FROM finaldb20.hero " +
            "WHERE name LIKE ?";

    private final static String UPDATE_HERO ="UPDATE finaldb20.hero SET name=?,rating=?,description=?," +
            "type=?,deleted=? WHERE id = ?";

    private final static String FIND_ALL_COMMENTS_BY_HERO_ID = "select hero.id,hero.name,hero.rating," +
            " hero.description,hero.type,hero.deleted,"+
    " comment.id,comment.content,comment.deleted,comment.user_id,comment.hero_id"+
    " from finaldb20.hero inner join finaldb20.comment on comment.hero_id = hero.id"+
    " where hero.id =(?) and hero.deleted=0 and comment.deleted=0";

    private final static String DELETE_HERO_BY_ID ="UPDATE finaldb20.hero SET deleted=? WHERE id = ?";

    private final static String RESTORE_HERO_BY_ID ="UPDATE finaldb20.hero SET deleted=? WHERE id = ?";

    private final static String SELECT_ALL_DELETED_HERO= "SELECT id,name,rating,description,type,deleted " +
            "FROM finaldb20.hero " +
            "WHERE deleted = 1";

    private final static String SELECT_IMAGE ="SELECT hero.id,image.id,image.image,image.hero_id FROM " +
            "finaldb20.image RIGHT JOIN finaldb20.hero ON " +
            "hero.id = image.hero_id WHERE hero.id = (?) ";


    /**
     * find a hero and all his comments by hero id
     * @param id
     * @return Map<Hero,ArrayList<Comment>>
     * @throws DAOException
     */
    public Map<Hero,ArrayList<Comment>> findAllCommentsByHeroId(int id) throws DAOException{
        Hero hero = new Hero();
        Comment comment ;
        ArrayList<Comment> comments = new ArrayList<>();
        Map<Hero,ArrayList<Comment>> result = new HashMap<>();
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(FIND_ALL_COMMENTS_BY_HERO_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            HeroBuilder builder = new HeroBuilder();
            CommentBuilder commentBuilder = new CommentBuilder();
            while (set.next()) {
                hero =builder.buildHero(set);
                comment = commentBuilder.build(set);
                comments.add(comment);
            }
            result.put(hero,comments);

        }catch (SQLException e){
            throw new DAOException("find all comment, hero dao error",e);
        }
        return result;
    }

    /**
     * find title imade from database
     * @param id
     * @return String
     * @throws DAOException
     */
    public String findImage(int id) throws DAOException {
        String image= null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_IMAGE)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                image=set.getString(DaoConstant.IMAGE);
            }
        }catch (SQLException e){
            throw new DAOException("hero dao 'findimage' error",e);
        }
        return image;
    }

    /**
     * find hero by id in database
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Hero findById(int id) throws DAOException {
        Hero hero= null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_HERO_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            HeroBuilder builder = new HeroBuilder();
            while (set.next()){
                hero =builder.buildHero(set);
            }
        }catch (SQLException e){
            throw new DAOException("hero dao 'findbyid' error",e);
        }
        return hero;
    }


    /**
     *
     * @param name
     * @return list <hero>
     * @throws DAOException
     */
    public List<Hero> searchByName(String name) throws DAOException {
        Hero hero;
        List<Hero> heroes= new ArrayList<>();
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SEARCH_BY_NAME)){
            statement.setString(1,"%"+name+"%");
            ResultSet set = statement.executeQuery();
            HeroBuilder builder = new HeroBuilder();
            while (set.next()){
                hero =builder.buildHero(set);
                heroes.add(hero);
            }
        }catch (SQLException e){
            throw new DAOException("hero dao 'searchbyname' error",e);
        }
        return heroes;
    }

    /**
     * select all hero from database
     * @return  List<Hero>
     * @throws DAOException
     */
    @Override
    public List<Hero> findAll() throws DAOException {
        List<Hero> result = new ArrayList<>();
        Hero  hero ;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_ALL)){
            ResultSet set = statement.executeQuery();
            HeroBuilder builder = new HeroBuilder();
            while (set.next()){
                hero =builder.buildHero(set);
                result.add(hero);
            }
        }catch (SQLException e){
            throw new DAOException("hero dao 'findall' error",e);
        }
        return result;
    }

    /**
     * add hero to database
     * @param entity
     * @throws DAOException
     */
    @Override
    public void add(Entity entity) throws DAOException {
        Hero hero = (Hero)entity;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(CREATE_HERO,Statement.RETURN_GENERATED_KEYS)) {
            HeroBuilder builder = new HeroBuilder();
            builder.fillStatement(hero,statement).execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            hero.setId(resultSet.getInt(1));
        }catch (SQLException e)
        {
            throw new DAOException("hero dao 'add' error",e);
        }
    }

    /**
     * marks a hero as deleted
     * @param id
     * @throws DAOException
     */
    @Override
    public void deleteById(int id) throws DAOException {
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(DELETE_HERO_BY_ID)){
            statement.setInt(1,1);
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("hero dao 'deletebyid' error",e);
        }
    }

    /**
     * restore hero by id
     * @param id
     * @throws DAOException
     */
    public void restoreById(int id) throws DAOException {
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(RESTORE_HERO_BY_ID)){
            statement.setInt(1,0);
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("hero dao 'restorebyid' error",e);
        }
    }

    /**
     * find all deleted heroes
     * @return  List<Hero>
     * @throws DAOException
     */
    public List<Hero> findAllDeleted() throws DAOException{
        List<Hero> result = new ArrayList<>();
        Hero  hero ;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_ALL_DELETED_HERO)){
            ResultSet set = statement.executeQuery();
            HeroBuilder builder = new HeroBuilder();
            while (set.next()){
                hero =builder.buildHero(set);
                result.add(hero);
            }
        }catch (SQLException e){
            throw new DAOException("hero dao 'findalldeleted' error",e);
        }
        return result;
    }

    /**
     * update hero in database
     * @param hero
     * @throws DAOException
     */
    public void update(Hero hero) throws DAOException{
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(UPDATE_HERO)){
            HeroBuilder builder = new HeroBuilder();
            statement.setInt(6,hero.getId());
            builder.fillStatement(hero,statement).executeUpdate();
        }catch (SQLException e){
            throw new DAOException("hero dao 'update' error",e);
        }
    }
}
