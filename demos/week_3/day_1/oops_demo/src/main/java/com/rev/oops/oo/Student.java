package demos.week_3.day_1.oops_demo.src.main.java.com.rev.oops.oo;

import java.util.Objects;

public class Student {
    String name;
    int age;
    double gpa;

    public static String university = "ABC University";
    public static int counter = 0;

    // Constructor
    public Student() {
        counter++;

    }

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        counter++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setGPA(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }

    public double getGPA() {
        return this.gpa;
    }

    @Override
    public String toString() {
        return "Student [name=" + this.name + ", age=" + this.age + ", gpa=" + this.gpa + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false; // not an object
        }
        if (obj.getClass() != this.getClass()) {
            return false; // not a student
        }
        Student other = (Student) obj; // type cast the generic Object into a Student
        return other.name == this.name && Double.compare(other.gpa, this.gpa) == 0
                && this.name.equals(other.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age, this.gpa);
    }

    public void enrollCourse(String courseName) {
        System.out.println("Enrolled in: " + courseName);
    }

}
