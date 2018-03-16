package com.zaksh.model.dao.interfaces;

import com.zaksh.model.pojo.Task;
import com.zaksh.model.pojo.User;

import java.util.List;

public interface AgendaDAO {
    List<Long> getAgendaFor(User user);
    boolean deleteTask(User user, Task task);
    boolean createTask(Long userId, Long taskId);
}
