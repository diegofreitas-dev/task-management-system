package model;

public class Tag {
    private int id;
    private String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name.toLowerCase();
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "; Name: " + name;
    }
}
