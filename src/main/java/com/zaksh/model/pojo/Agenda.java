package com.zaksh.model.pojo;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private User user;
    private ArrayList<Task> tasks;

    public  Agenda(User user, List<Task> tasks) {
        this.user = user;
        this.tasks = new ArrayList<>(tasks);
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
