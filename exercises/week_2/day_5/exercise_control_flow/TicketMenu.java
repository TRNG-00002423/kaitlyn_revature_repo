import java.util.Scanner;

/**
 * Week 2 Exercise — menu-driven console (implement the menu loop).
 *
 * Compile: javac TicketMenu.java
 * Run: java TicketMenu
 */
public class TicketMenu {

    public static void printArray(String[] arr) {
        for (String str : arr) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        String[] tickets = { "BUG-101 Login timeout", "BUG-102 CSV import", "BUG-103 flaky assertion" };
        int[] priorities = { 2, 2, 2 }; // stretch: update in menu option 2

        try (Scanner in = new Scanner(System.in)) {
            // print menu: 1=list 2=set priority 3=summary 4=quit
            boolean quit = false;
            while (!quit) {

                System.out.println("Options: 1=list, 2=set priority, 3=summary, 4=quit");
                String userInput = in.next();
                userInput = userInput.trim().toLowerCase();

                switch (userInput) {
                    case "1":
                    case "list":
                        printArray(tickets);
                        break;
                    case "2":
                    case "set priority":
                        System.out.println("Enter the index of the bug you would like to change the priority of:");
                        userInput = in.next();
                        int index;
                        try {
                            index = Integer.parseInt(userInput);
                            if (index >= priorities.length || index < 0) {
                                throw new IndexOutOfBoundsException();
                            }
                            int oldPriority = priorities[index];
                            System.out.println(
                                    "The priority of " + tickets[index] + " is currently " + oldPriority + ".");
                            System.out.println("Enter a new priority (1-3) for " + tickets[index] + ":");
                            userInput = in.next();
                            int newPriority = Integer.parseInt(userInput);
                            if (newPriority < 1 || newPriority > 3) {
                                throw new ArithmeticException();
                            }
                            priorities[index] = newPriority;
                            System.out.println("Successfully changed priority of " + tickets[index] + " from "
                                    + oldPriority + " to " + newPriority + ".");
                        } catch (NumberFormatException e) {
                            System.out.println("Set priority failed: Index is not a number.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Set priority failed: Index is out of bounds.");
                        } catch (ArithmeticException e) {
                            System.out.println("Set priority failed: priority must be between 1 and 3.");
                        }

                        break;
                    case "3":
                    case "summary":
                        String tickStr = tickets.length == 1 ? "ticket" : "tickets";
                        System.out.println(tickets.length + " " + tickStr);
                        break;
                    case "4":
                    case "quit":
                        System.out.println("Quitting application.");
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid option.");

                }

            }
        }
    }
}