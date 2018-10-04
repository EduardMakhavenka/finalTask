package by.makhavenka.task.entity;

public class Hero extends Entity {
    private int id;
    private String name;
    private int rating;
    private String description;
    private String type;
    private int deleted;

    public Hero(int id, String name, int rating, String description, String type, int deleted) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.type = type;
        this.deleted = deleted;
    }

    public Hero(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;

        Hero hero = (Hero) o;

        if (id != hero.id) return false;
        if (rating != hero.rating) return false;
        if (deleted != hero.deleted) return false;
        if (name != null ? !name.equals(hero.name) : hero.name != null) return false;
        if (description != null ? !description.equals(hero.description) : hero.description != null) return false;
        return type != null ? type.equals(hero.type) : hero.type == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + deleted;
        return result;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
