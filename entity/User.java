package entity;

public class User {
    private  int id;
    private String name;
    private String password;
    private int roleid;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User() {
    }

    public User(int id, String username, String password) {
        this.id=id;
        this.name = username;
        this.password = password;
    }

    public User(String name, String password, int roleid) {
        this.name = name;
        this.password = password;
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
