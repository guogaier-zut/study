package entity;

import java.util.Date;

public class Equipment {
    private int id;
    private String name;
    private String type;
    private String manager;
    private String tip;
    private String date;

    public Equipment(int id, String name, String type, String manager, String tip, String date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.manager = manager;
        this.tip = tip;
        this.date = date;
    }
    public Equipment(int id, String name, String type, String manager, String tip) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.manager = manager;
        this.tip = tip;
    }
    public Equipment(String name, String type, String manager, String tip) {
        this.name = name;
        this.type = type;
        this.manager = manager;
        this.tip = tip;
    }

    public Equipment() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", manager='" + manager + '\'' +
                ", tip='" + tip + '\'' +
                ", date=" + date +
                '}';
    }
}
