package malfs.utils.list.sort.model;


public class Cat {
    public Integer id;
    public Double weight;
    public Integer age;
    public String color;

    public Cat(int id, double weight, int age, String color) {
        this.id = id;
        this.weight = Double.valueOf(weight);
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "cat = [id=" + id + " weight=" + weight + " age=" + age + " color=" + color + "]";
    }
}
