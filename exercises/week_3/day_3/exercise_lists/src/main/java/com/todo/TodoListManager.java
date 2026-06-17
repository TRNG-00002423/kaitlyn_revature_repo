package com.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListManager {
    private final List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public String getTask(int index) {
        if (index > this.tasks.size() || index < 0) {
            throw new IndexOutOfBoundsException("No task at index " + index);
        }
        return this.tasks.get(index);
    }

    public void completeTask(int index) {
        if (index > this.tasks.size() || index < 0) {
            throw new IndexOutOfBoundsException("No task at index " + index);
        }
        this.tasks.remove(index);
    }

    public List<String> listTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }
}