package com.taskmanager.service;

import com.taskmanager.dto.CreateSubTaskDTO;
import com.taskmanager.dto.TaskDTO;
import com.taskmanager.model.SubTask;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskDB;
import com.taskmanager.repo.TaskRepo;
import com.taskmanager.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInterace{
    private final TaskRepo repository;

    @Autowired
    public TaskService(TaskRepo taskRepo){
        this.repository = taskRepo;
    }

    @Override
    public List<TaskDTO> getAllTasks(){
        return repository.findAll().stream().map(this::convert).collect(Collectors.toList());
        /*return repository.findAll().stream().map(h -> {
            TaskDTO dto = new TaskDTO();
            dto.setId(h.getId());
            dto.setTitle(h.getTitle());
            dto.setDescription(h.getDescription());
            dto.setDate(h.getDate());
            return dto;
        }).collect(Collectors.toList());*/
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task = repository.save(task);
        return convert(task);
    }

    public Task getTaskById(long id){
        Task task = new Task();
        task = repository.getOne(id);
        return task;
    }



    public void editTask(Task task){
        Task t = getTaskById(task.getId());
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setDate(task.getDate());
        t.setSubtasks(task.getSubTasks());
        repository.findAll().add((int) task.getId(), t);
        repository.flush();
    }

    public void addSubTask(SubTask subTask) {
        Task task = getTaskById(subTask.getTask());
        task.addSubTask(subTask);
        repository.save(task);

    }


    private TaskDTO convert(Task task){
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setDate(task.getDate());
        dto.setDescription(task.getDescription());
        dto.setTitle(task.getTitle());
        return dto;
    }
}
