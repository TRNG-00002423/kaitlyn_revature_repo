package com.todo;

public class TodoDemo {
    public static void main(String[] args) {
        // TODO: add tasks, print, complete one, print again

        // initialize todo list and add some tasks
        TodoListManager myList = new TodoListManager();
        myList.addTask("walk the dog");
        myList.addTask("walk the cat");
        myList.addTask("walk the fish");

        System.out.println(myList.listTasks());

        // walk the dog, then print tasks again
        myList.completeTask(0);
        System.out.println(myList.listTasks());

        // Trying to access an out-of-bounds index will result in an exception
        try {
            String task = myList.getTask(5);
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        // The same is applicable for a negative index
        try {
            myList.completeTask(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

    }
}