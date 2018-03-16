package com.zaksh.model.dao.interfaces;

import com.zaksh.model.pojo.Task;

import java.sql.Date;

public interface TaskDAO extends DAO<Task, Long> {
    Task createTask(String name, String description, Date startDate, Date endDate);
}
