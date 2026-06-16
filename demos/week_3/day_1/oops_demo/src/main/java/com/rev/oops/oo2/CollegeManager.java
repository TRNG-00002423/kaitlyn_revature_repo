package demos.week_3.day_1.oops_demo.src.main.java.com.rev.oops.oo2;

public class CollegeManager {
    public static void main(String[] args) {
        Student student = new Student();
        Professor professor = new Professor();

        Student[] students = new Student[5];

        students[0] = new Student();
        students[1] = new Student();

        // upcasting
        Student s = new Student();
        Person p = s;

        // downcasting
        Person p2 = new Student();
        Student s2 = (Student) p2;
    }
}
