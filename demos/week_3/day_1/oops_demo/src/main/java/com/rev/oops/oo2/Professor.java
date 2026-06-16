package demos.week_3.day_1.oops_demo.src.main.java.com.rev.oops.oo2;

public class Professor extends Person {
    private double salary;

    public Professor() {

    }

    public Professor(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public void introduce() {
        System.out.println("Hello, I am a Professor");
    }
}
