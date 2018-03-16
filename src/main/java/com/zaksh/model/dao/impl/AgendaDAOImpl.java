package com.zaksh.model.dao.impl;

import com.zaksh.model.dao.interfaces.AgendaDAO;
import com.zaksh.model.pojo.Task;
import com.zaksh.model.pojo.User;
import com.zaksh.model.utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class AgendaDAOImpl implements AgendaDAO {

    private static Logger logger = Logger.getLogger(AgendaDAOImpl.class.getName());

    @Override
    public List<Long> getAgendaFor(User user) {
        LinkedList<Long> taskIDs = new LinkedList<>();

        String sql = " SELECT task_id FROM users_tasks WHERE user_id = " + user.getId() + ";";


        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                taskIDs.add(resultSet.getLong("task_id"));
            }

            if (taskIDs.size() > 0) {
                logger.info("Task are found for user" + user.getLogin());
            } else {
                logger.info("Zero tasks found for user" + user.getLogin());
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.info("User " + user.getLogin() + " is not found. SQLException = " + e.getSQLState());
        }

        return taskIDs;
    }

    @Override
    public boolean deleteTask(User user, Task task) {

        String sql = "DELETE FROM users_tasks WHERE task_id=? AND user_id=?;";


        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, task.getId());
            statement.setLong(2, user.getId());

            statement.executeUpdate();

            statement.close();
            connection.close();

            logger.info("Task " + task.getName() + " for user" + user.getLogin() + " is successfully deleted");
            return true;
        } catch (SQLException e) {
            logger.info("Task " + task.getName() + " for user" + user.getLogin() + " failed to delete. "
                    + "SQLException = " + e.getSQLState());
        }
        return false;
    }

    @Override
    public boolean createTask(Long userId, Long taskId) {
        String sql = "INSERT INTO users_tasks VALUES(?,?);";


        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);

            statement.executeUpdate();

            statement.close();
            connection.close();

            logger.info("TaskId " + taskId + " and userId" + userId + " are successfully bineded");
            return true;
        } catch (SQLException e) {
            logger.info("TaskId " + taskId + " and user" + userId + " are failed to bind. "
                    + "SQLException = " + e.getSQLState());
        }
        return false;
    }
}
