package com.phxc.notenayhandmade.Models;
public class Task {
    private String name;
    private boolean isCompleted;
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }
    public String getName() {
        return name;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
