package by.makhavenka.task.entity;


/**
 * public class user extends entity.
 * Contains int id,String name,String surname,String login,String mail,
 * String password,int rating,int banned,String role,String photo,
 * String phone,String sex,int age,String favotiteHero,String nickName;
 */
public class User extends Entity {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String mail;
    private String password;
    private int rating;
    private int banned;
    private String role;
    private String photo;
    private String phone;
    private String sex;
    private int age;
    private String favotiteHero;
    private String nickName;

    public User() { }

    public User(int id, String name, String surname,
                String login, String mail, String password,
                int rating, int banned, String role,
                String photo, String phone, String sex,
                int age, String favotiteHero, String nickName) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.rating = rating;
        this.banned = banned;
        this.role = role;
        this.photo = photo;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.favotiteHero = favotiteHero;
        this.nickName = nickName;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavotiteHero() {
        return favotiteHero;
    }

    public void setFavotiteHero(String favotiteHero) {
        this.favotiteHero = favotiteHero;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (rating != user.rating) return false;
        if (banned != user.banned) return false;
        if (age != user.age) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (photo != null ? !photo.equals(user.photo) : user.photo != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (favotiteHero != null ? !favotiteHero.equals(user.favotiteHero) : user.favotiteHero != null) return false;
        return nickName != null ? nickName.equals(user.nickName) : user.nickName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + banned;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (favotiteHero != null ? favotiteHero.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                ", banned=" + banned +
                ", role='" + role + '\'' +
                ", photo='" + photo + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", favotiteHero='" + favotiteHero + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
