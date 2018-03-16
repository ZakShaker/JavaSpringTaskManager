package com.zaksh.model.pojo;

public class UsersTasks {

    private long userId;
    private long taskId;

    public UsersTasks(long userId, long taskId){
        this.userId = userId;
        this.taskId = taskId;
    }

    public long getTaskId() {
        return taskId;
    }

    public long getUserId() {
        return userId;
    }
}
