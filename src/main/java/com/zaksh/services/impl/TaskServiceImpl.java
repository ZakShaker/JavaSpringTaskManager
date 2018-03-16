package com.zaksh.services.impl;

import com.zaksh.model.dao.impl.TaskDAOImpl;
import com.zaksh.model.dao.interfaces.TaskDAO;
import com.zaksh.model.pojo.Task;
import com.zaksh.services.interfaces.TaskService;

import java.sql.Date;

public class TaskServiceImpl implements TaskService {

    private static TaskDAO taskDao = new TaskDAOImpl();

    @Override
    public Task createTask(String name, String description, Date startDate, Date endDate) {
        return taskDao.createTask(name, description, startDate, endDate);
    }
}

