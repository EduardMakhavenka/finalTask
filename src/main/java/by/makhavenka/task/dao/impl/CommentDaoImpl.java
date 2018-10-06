package by.makhavenka.task.dao.impl;

import by.makhavenka.task.dao.CommentDao;

import by.makhavenka.task.dao.builder.CommentBuilder;
import by.makhavenka.task.dao.DaoConstant;
import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.entity.Entity;
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

public class CommentDaoImpl implements CommentDao {

    private final static String ADD_COMMENT = " INSERT INTO finaldb20.comment (content,deleted,user_id,hero_id) " +
            " VALUES (?,?,?,?)";

    private final static String SELECT_ALL = "SELECT id,content,deleted,user_id,hero_id " +
            "FROM finaldb20.comment";

    private final static String FIND_COMMENT_BY_ID = "SELECT id,content,deleted,user_id,hero_id " +
            "FROM finaldb20.comment WHERE user_id = (?) AND deleted = 0";

    private final static String FIND_BY_ID = "SELECT id,content,deleted,user_id,hero_id " +
            "FROM finaldb20.comment WHERE comment.id = (?) AND deleted = 0";

    private final static String FIND_ALL_BY_ID = "SELECT user.id, comment.content, hero.name " +
            "FROM finaldb20.user INNER JOIN finaldb20.comment ON comment.user_id = user.id " +
            "LEFT JOIN finaldb20.hero ON comment.hero_id = hero.id "+
            "WHERE user.id = (?)";

    private final static String DELETE_COMMENT_BY_ID ="UPDATE finaldb20.comment SET deleted=? WHERE id = ?";

    /**
     * Find a comment on the user’s hero
     * @param idUser
     * @return Map<String,String>
     * @throws DAOException
     */
    public Map<String,String> findAllById(int idUser)throws  DAOException{
        String content;
        String name;
        Map<String,String> result = new HashMap<>();
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(FIND_ALL_BY_ID)){
            statement.setInt(1,idUser);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                content = set.getString(DaoConstant.CONTENT);
                name = set.getString(DaoConstant.NAME);
                result.put(content,name);
            }
        }
        catch (SQLException e){
            throw new DAOException("comment dao 'findallbyid' error",e);
        }
        return result;
    }

    /**
     * Find comment by user id in database
     * @param id
     * @return object comment
     * @throws DAOException
     */
    @Override
    public Comment findById(int id) throws DAOException {
        Comment comment = null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(FIND_COMMENT_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            comment = new Comment();
            CommentBuilder builder = new CommentBuilder();
            while (set.next()){
                comment=builder.build(set);
        }}
        catch (SQLException e){
            throw new DAOException("comment dao 'findbyid' error",e);
        }
        return comment;
    }

    /**
     * find comment by comments id
     * @param id
     * @return object comment
     * @throws DAOException
     */
    public Comment findCommentById(int id) throws DAOException {
        Comment comment = null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(FIND_BY_ID)){
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            CommentBuilder builder = new CommentBuilder();
            while (set.next()) {
                comment = builder.build(set);
            }
        }
        catch (SQLException e){
            throw new DAOException("comment dao 'findCommentbyid' error",e);
        }
        return comment;
    }

    /**
     * find all comments in data base
     * @return list<comment>
     * @throws DAOException
     */
    @Override
    public List findAll() throws DAOException {
        List<Comment> result= new ArrayList<>();
        Comment comment = null;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(SELECT_ALL)){
            ResultSet set = statement.executeQuery();
            CommentBuilder builder = new CommentBuilder();
            while (set.next()){
                comment = builder.build(set);
                result.add(comment);
            }
        }
        catch (SQLException e){
            throw new DAOException("comment dao 'findall' error",e);
        }
        return result;
    }

    /**
     * add comment to database
     * @param entity
     * @throws DAOException
     */
    @Override
    public void add(Entity entity) throws DAOException {
        Comment comment = (Comment)entity;
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(ADD_COMMENT,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comment.getContent());
            statement.setInt(2, comment.getDeleted());
            statement.setInt(3, comment.getUserId());
            statement.setInt(4, comment.getHeroId());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            comment.setId(resultSet.getInt(1));
        }catch (SQLException e)
        {
            throw new DAOException("comment dao 'add' error",e);
        }
    }

    /**
     * marks a comment as deleted
     * @param id
     * @throws DAOException
     */
    @Override
    public void deleteById(int id) throws DAOException {
        try(ConnectionProxy connectionProxy = Pool.getInstance().takeConnection();
            PreparedStatement statement = connectionProxy.prepareStatement(DELETE_COMMENT_BY_ID)){
            statement.setInt(1,1);
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("comment dao 'deletebyid' error",e);
        }
    }
}
