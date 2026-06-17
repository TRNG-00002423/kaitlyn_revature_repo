package com.collections;

public class Student implements Comparable {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }

    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public int compareTo(Object other) {
        Student otherStudent = (Student) other;
        return this.compareTo(otherStudent);
    }

}
