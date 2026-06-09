/**
 * Greets the user, then prints the current Java runtime version.
 * Has two optional arguments, one for first and one for last name.
 * If no arguments are given, generically greet trainee.
 * 
 * @param first_name
 * @param last_name
 * @return void
 */

public class HelloWeek2 {
    public static void main(String[] args) {
        if (args.length >= 2) {
            System.out.println("Hello, " + args[0] + " " + args[1] + "!");
        } else if (args.length >= 1) {
            System.out.println("Hello, " + args[0] + "!");
        } else {
            System.out.println("Hello, trainee!");
        }
        System.out.println("Java version: " + System.getProperty("java.version"));
    }
}