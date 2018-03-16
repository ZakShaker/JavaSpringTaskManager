package com.zaksh.services.interfaces;

import com.zaksh.model.pojo.Task;
import com.zaksh.model.pojo.User;

import java.util.List;

public interface AgendaService {
    List<Task> getAgenda(User user);
    boolean bindTask(Long userId, Long taskId);
    boolean deleteTask(User user, Task task);
}
