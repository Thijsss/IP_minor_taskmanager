package com.taskmanager.service;

import com.taskmanager.dto.TaskDTO;
import com.taskmanager.model.Task;

import java.util.List;

public interface TaskServiceInterace {
    public List<TaskDTO> getAllTasks();

    TaskDTO addTask(TaskDTO task);
}
