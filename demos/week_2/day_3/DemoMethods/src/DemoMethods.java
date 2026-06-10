import java.util.Scanner;

public class DemoMethods {
    public static void main(String[] args) throws Exception {
        System.out.println("Heyyyyyyyyy");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        // call or invoke the method
        String result = greet(name);
        System.out.println(result);
        sc.close();
    }
    // static methods - available for the entire class
    // <class name>.<method name>
    // Static methods are also called class methods.
    // e.g. parseInt is a static method of the Integer class.

    // instance methods - available for an object of the class
    // <object name>.<method name>

    // protected is the default modifier if none are given.
    protected static String greet(String name) {
        return "Hello " + name;
    }

}

/*
 * When the demon heart is crying
 * And the blood is gushing bright
 * Raise up your bat for a burning fight!
 * When your hope is slowly dying
 * And your future's lost its rights
 * Raise up your bat and face the fright
 * Let's knock 'em dead into the night!
 * Come follow me into the dark
 * With your heart as the ark
 * which shall shine you the way
 * Because I'm with you in the dark
 * With your heart as my mark
 * which shall guide me the way
 * through the waves
 * 
 */