package com.zaksh.model.dao.impl;

import com.zaksh.model.dao.interfaces.TaskDAO;
import com.zaksh.model.pojo.Task;
import com.zaksh.model.utils.DataSourceFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;


public class TaskDAOImpl implements TaskDAO {

    private static Logger logger = Logger.getLogger(TaskDAOImpl.class.getName());

    @Override
    public Task getById(Long id) {
        Task task = null;

        String sql = " SELECT * FROM tasks WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            task = new Task(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"));

            logger.info(task.getName() + " - task is found");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.info("Task with id " + id + " is not found. SQLException = " + e.getSQLState());
        }

        return task;
    }

    @Override
    public Task createTask(String name, String description, Date startDate, Date endDate) {
        Task task = null;

        String sql = "insert into tasks(name, description, start_date, end_date) values(?, ?, ?, ?) RETURNING id;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDate(3, startDate);
            statement.setDate(4, endDate);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                task = new Task(
                        res.getLong(1),
                        name,
                        description,
                        startDate,
                        endDate);
                logger.info(task.getName() + " - task is created");
            } else {
                logger.info("Creating task failed, no ID obtained.");
            }
            res.close();
            statement.close();
            connection.close();
        } catch (
                SQLException e)

        {
            logger.info("Task " + " is not created. SQLException = " + e.getSQLState());
        }

        return task;
    }

    @Override
    public List<Task> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public Task save(Task entity) {
        throw new NotImplementedException();
    }

    @Override
    public Long insert(Task entity) {
        throw new NotImplementedException();
    }

    @Override
    public int update(Task entity) {
        throw new NotImplementedException();
    }

    @Override
    public int delete(Task entity) {
        throw new NotImplementedException();
    }
}
