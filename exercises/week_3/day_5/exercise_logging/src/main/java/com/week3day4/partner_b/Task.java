package com.week3day4.partner_b;

/**
 * Partner B — comparable task for PriorityQueue.
 * : implement Comparable<Task> (document ordering rule)
 */
public class Task implements Comparable<Task> {
    // fields priority, description
    private int priority;
    private String description;

    // ctor, getters, toString

    public Task(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    // lower priority number = higher priority
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Priority: " + priority + ", Description:" + description;
    }
}