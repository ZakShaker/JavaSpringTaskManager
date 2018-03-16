package com.zaksh.controllers;

import com.zaksh.model.pojo.Task;
import com.zaksh.model.pojo.User;
import com.zaksh.model.pojo.UsersTasks;
import com.zaksh.services.interfaces.AgendaService;
import com.zaksh.services.interfaces.TaskService;
import com.zaksh.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@SessionAttributes("userId")
public class DashboardController {
    private static Logger logger = Logger.getLogger(DashboardController.class.getName());

    private static AgendaService agendaService;
    private static UserService userService;
    private static TaskService taskService;

    @Autowired
    public DashboardController(AgendaService agendaService, UserService userService, TaskService taskService) {
        this.agendaService = agendaService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @RequestMapping(value = "/my_dashboard", method = RequestMethod.GET)
    protected ModelAndView showDashboard(ModelMap model) {
        logger.info("Dashboard is entered");

        try {
            Long userId;
            if (model.containsAttribute("userId")) {
                userId = (Long) model.get("userId");

                User user = userService.find(userId);

                List<Task> my_tasks = null;
                if (user != null) {
                    my_tasks = agendaService.getAgenda(user);
                }

/*
                if (my_tasks != null) {

                    if (model.containsAttribute("idToDelete")) {
                        Long id = null;
                        try {
                            id = (Long) model.get("idToDelete");
                            Task toDelete = findTask(my_tasks, id);
                            if (agendaService.deleteTask(user, toDelete)) {
                                my_tasks.remove(toDelete);
                            }

                        } catch (NumberFormatException ex) {
                            logger.info("Dashboard Servlet doPost: Cannot parse idToDelete ");
                        }

                    } else if (model.containsAttribute("idToEdit")) {
                        //TODO:
                    }

                } else {
                    my_tasks = new ArrayList<>(0);
                    logger.info("The list of tasks is empty ");
                }
*/

                if (my_tasks == null) {
                    my_tasks = new ArrayList<>(0);
                    logger.info("The list of tasks is empty ");
                }


                model.addAttribute("tasks", my_tasks);
                model.addAttribute("userId", userId);
                return new ModelAndView("/my_dashboard", model);
            }

        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    protected ModelAndView deleteTask(@ModelAttribute UsersTasks usersTasks, ModelMap model) {
        logger.info("Dashboard is entered");

        User user = userService.find(usersTasks.getUserId());

        List<Task> my_tasks = null;
        if (user != null) {
            my_tasks = agendaService.getAgenda(user);
        }

        if (my_tasks != null) {
            Long id = null;
            id = usersTasks.getTaskId();
            Task toDelete = findTask(my_tasks, id);
            if (agendaService.deleteTask(user, toDelete)) {
                logger.info("Task with id " + id + " is successfully deleted");
            }
        } else {
            logger.info("The list of tasks is empty ");
        }

        model.addAttribute("userId", usersTasks.getUserId());
        return new ModelAndView("redirect:/my_dashboard", model);
    }

    @RequestMapping(value = "/create_user_task", method = RequestMethod.GET)
    protected ModelAndView createUserTask(@ModelAttribute("userId") long userId, ModelMap model) {
        model.addAttribute("userId", userId);
        return new ModelAndView("/create_task", model);
    }

    @RequestMapping(value = "/create_task", method = RequestMethod.POST)
    protected ModelAndView createTask(@ModelAttribute("userId") long userId, @ModelAttribute("name") String name, @ModelAttribute("description") String description, @ModelAttribute("start_date") String startDate, @ModelAttribute("end_date") String endDate, ModelMap model) {

        logger.info("CreateTaskServlet doPost");

        try {
            Date s_Date = Date.valueOf(startDate);
            Date e_Date = Date.valueOf(endDate);

            Task task = taskService.createTask(name, description, s_Date, e_Date);

            if (task != null) {
                if (agendaService.bindTask(userId, task.getId())) {
                    model.addAttribute("userId", userId);
                    return new ModelAndView("redirect:/my_dashboard", model);
                }  //else TODO: error message on FrontEnd
            } //else TODO: error message on FrontEnd

        } catch (IllegalArgumentException ex) {
            logger.info("Exception: wrong date format");
            model.addAttribute("userId", userId);
            return new ModelAndView("/create_task", model);
        }

        model.addAttribute("userId", userId);
        return new ModelAndView("/create_task", model);
    }

    private Task findTask(List<Task> tasks, Long taskID) {
        Task toBeFound = null;
        for (Task task : tasks) {
            if (taskID.equals(task.getId())) {
                toBeFound = task;
                break;
            }
        }
        return toBeFound;
    }
}
