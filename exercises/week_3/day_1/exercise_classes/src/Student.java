import java.util.Objects;

/**
 * Lab 2 — Student. Replace UnsupportedOperationException bodies with real
 * logic.
 * See ../README.md
 */
public class Student {

    private final int id;
    private String name;
    private String program;
    private static int nextId = 0;

    public Student(String name, String program) {
        this.name = name;
        this.program = program;
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getProgram() {
        return this.program;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public static int getEnrollmentCount() {
        // Currently nextId is always equal to the number of students. If the
        // functionality to unenroll students was added, this code would stop making
        // sense. For now, though, the simplicity of this is nice.
        return nextId;
    }

    @Override
    public String toString() {
        return "Student [id=" + this.id + ", name=" + this.name + ", program=" + this.program + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Student other = (Student) o;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}