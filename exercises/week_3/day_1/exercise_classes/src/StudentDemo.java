/** Lab 2 driver — run after Student is implemented. */
public class StudentDemo {
    public static void main(String[] args) {
        // TODO: create 3 Student instances, print enrollment count,
        // demonstrate equals vs == with two references to same id scenario if possible
        Student blossom = new Student("Blossom", "Software Development");
        Student bubbles = new Student("Bubbles", "Web Design");
        Student buttercup = new Student("Buttercup", "Cybersecurity");

        Student[] students = { blossom, bubbles, buttercup };
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Enrollment count: " + Student.getEnrollmentCount());

        System.out.println(
                "Creating two students with the same id is impossible, as there is no direct setter for id and duplicates are not allowed.");

        Student blossomCopy = new Student("Blossom", "Software Development");
        System.out.println(blossomCopy);
        System.out.println("blossom == blossomCopy is " + (blossom == blossomCopy));
        System.out.println("blossom.equals(blossomCopy) is " + (blossom.equals(blossomCopy)));
        System.out.println(
                "Both results are false because despite the other values being the same, the ids of the students are different.");
    }
}