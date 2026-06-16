package demos.week_3.day_1.oops_demo.src.main.java.com.rev.oops.oo;

public class StudentManager {
    // When no constrcutor is provided, the JVM provides us with a default
    // constructor.

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Audy");
        s1.setAge(24);
        s1.setGPA(3.8);

        Student s2 = new Student("Utsav", 23, 3.9);

        System.out.println(Student.university);
        System.out.println(Student.counter);

        // the getClass() method was never explicitly defined.
        // The getClass() method will return a "fully qualified class name", which
        // includes the name of the package as well as the name of the class.
        System.out.println(s2);

        Student s3 = new Student("Steve", 25, 2.0);
        Student s4 = new Student("Steve", 25, 2.0);

        System.out.println(s3 == s4); // false
        System.out.println(s3.equals(s4)); // true

        s3.enrollCourse("Java");

    }

}
