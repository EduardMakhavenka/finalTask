package by.makhavenka.task.entity;

public class Comment extends Entity {
    private int id;
    private String Content;
    private int deleted;
    private int userId;
    private int heroId;

    public Comment(){}

    public Comment(int id, String content, int deleted, int userId, int heroId) {
        this.id = id;
        Content = content;
        this.deleted = deleted;
        this.userId = userId;
        this.heroId = heroId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (deleted != comment.deleted) return false;
        if (userId != comment.userId) return false;
        if (heroId != comment.heroId) return false;
        return Content != null ? Content.equals(comment.Content) : comment.Content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (Content != null ? Content.hashCode() : 0);
        result = 31 * result + deleted;
        result = 31 * result + userId;
        result = 31 * result + heroId;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Content='" + Content + '\'' +
                ", deleted=" + deleted +
                ", userId=" + userId +
                ", heroId=" + heroId +
                '}';
    }
}
