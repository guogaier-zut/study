package entity;

public class Area {
    int id;
    String building;
    String floor;

    public Area(){}
    public Area(int id, String building, String floor) {
        this.id = id;
        this.building = building;
        this.floor = floor;
    }

    public Area(String building, String floor) {
        this.building = building;
        this.floor = floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}
