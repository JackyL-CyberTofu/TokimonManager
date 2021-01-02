package ca.cmpt213.asn4.web.models;

/**
 *  Tokimon Class
 *  Developer: Jacky Lim
 */


public class Tokimon extends Object{

    static private long total = 0;
    private int pid;
    private String name;
    private int weight;
    private int height;
    private String ability;
    private int strength;
    private String color;

    public Tokimon(){

    }

    public Tokimon(String name, int weight, int height, String ability, int strength, String color){
        total++;
        this.pid = (int) total;
        this.name = name;
        this.weight= weight;
        this.height = height;
        this.ability = ability;
        this.strength = strength;
        this.color = color;
    }

    public Tokimon(int pid, String name, int weight, int height, String ability, int strength, String color){
        total++;
        this.pid = pid;
        this.name = name;
        this.weight= weight;
        this.height = height;
        this.ability = ability;
        this.strength = strength;
        this.color = color;
    }

    public void setName(String name) { this.name = name; }

    public void setWeight(int weight) { this.weight = weight; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public String getAbility() { return ability; }

    public void setAbility(String ability) { this.ability = ability; }

    public int getStrength() { return strength; }

    public void setStrength(int strength) { this.strength = strength; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public void setPid(int pid) { this.pid = pid; }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public int getWeight() { return weight; }


}
