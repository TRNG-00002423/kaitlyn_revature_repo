package demos.week_3.day_1.oops_demo.src.main.java.com.rev.oops.oo2;

public class Student extends Person implements Loginable {
    private double gpa;

    public Student() {

    }

    public Student(String name, int age, double gpa) {
        super(name, age);
        this.gpa = gpa;
    }

    public void enrollCourse(String course) {
        System.out.println(course);
    }

    public void enrollCourse(String course, int semester) {
        System.out.println(course + " " + semester);
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I am a Student");
    }

    public void login() {

    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
