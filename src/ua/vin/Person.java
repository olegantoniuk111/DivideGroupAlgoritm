package ua.vin;

public class Person implements Comparable<Person> {

    private int weight;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {

        return weight;
    }

    public Person(int weight) {

        this.weight = weight;
    }

    @Override
    public int compareTo(Person o) {
        Integer weight = o.getWeight();
        return Integer.valueOf(this.weight).compareTo(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return weight == person.weight;
    }

    @Override
    public int hashCode() {
        return weight;
    }
}
