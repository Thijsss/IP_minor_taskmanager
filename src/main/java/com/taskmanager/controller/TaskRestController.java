package com.taskmanager.controller;

import com.taskmanager.dto.TaskDTO;
import com.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService ts){
        this.taskService = ts;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/task")
    public TaskDTO createNewTask(@Valid TaskDTO taskDTO){
        return taskService.addTask(taskDTO);
    }
}
