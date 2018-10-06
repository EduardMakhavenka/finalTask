package by.makhavenka.task.entity;

/**
 * abstract class entity.
 * Contains int id.
 */
public abstract class Entity {
    private int id;

    public Entity(){}
    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
