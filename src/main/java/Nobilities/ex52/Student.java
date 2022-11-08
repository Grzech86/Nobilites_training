package Nobilities.ex52;

import java.util.Arrays;

class Student {
    public final String name;
    public final int age;
    public final String city;
    public final double grade;
    private final String[] faculty;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", grade=" + grade +
                ", faculty=" + Arrays.toString(faculty) +
                '}';
    }

    private Student(String name, int age, String city, double grade, String[] faculty) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.grade = grade;
        this.faculty = faculty;
    }

    static Student decode(String line) {
        String[] elements = line.split(",");

        return new Student(
                elements[0],
                Integer.valueOf(elements[1]),
                elements[2],
                Double.valueOf(elements[3]),
                Arrays.copyOfRange(elements, 4, elements.length)
        );
    }

}