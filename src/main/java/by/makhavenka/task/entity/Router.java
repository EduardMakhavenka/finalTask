package by.makhavenka.task.entity;

/**
 * class router . Contains Sting page,
 * Type(enum) type.
 */
public class Router {
    private String page;
    private Type type;

    public enum Type{
        FORWARD,REDIRECT;
    }

    public Router(){}

    public Router(String page, Type type){
        this.page= page;
        this.type=type;
    }

    /**
     * sets forward or redirect, then returns page
     * @return String
     */
    public String getPage(){
        if(type.equals(Type.FORWARD)){
            return page;
        }
        return page.substring(1);
    }

    public void setPage(String path) {this.page = path;}
    public Type getType() {return  type;}
    public void setType(Type type) {this.type = type;}
}
