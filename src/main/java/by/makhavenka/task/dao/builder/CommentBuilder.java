package by.makhavenka.task.dao.builder;

import by.makhavenka.task.dao.DaoConstant;
import by.makhavenka.task.entity.Comment;
import by.makhavenka.task.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentBuilder {

    /**
     * Build object comment from result set.
     * @param set
     * @return comment
     * @throws DAOException
     */
    public Comment build(ResultSet set) throws DAOException{
        Comment comment = new Comment();
        try {
            comment.setId(set.getInt(DaoConstant.COMMENT_ID));
            comment.setContent(set.getString(DaoConstant.CONTENT));
            comment.setDeleted(set.getInt(DaoConstant.DELETED));
            comment.setUserId(set.getInt(DaoConstant.USER_ID));
            comment.setHeroId(set.getInt(DaoConstant.HERO_ID));
        } catch (SQLException e) {
            throw new DAOException("dao builder comment error",e);
        }
        return comment;
    }
}
