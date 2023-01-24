package me.kktrkkt.java8to11.api.defaultMethod;

public class Fruit {
    private String name;
    private int year;

    public Fruit(String name, int year) {
       this.name = name;
       this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
