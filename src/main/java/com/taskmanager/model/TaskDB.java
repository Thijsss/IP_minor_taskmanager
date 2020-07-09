package com.taskmanager.model;

import com.taskmanager.dto.CreateSubTaskDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TaskDB {
    private ArrayList<Task> tasks;

    public TaskDB(){
        tasks = new ArrayList<>();
        LocalDateTime date = LocalDateTime.of(2020,03,20,10,0,0);
        Task task1 = new Task("Task 1", date, "tanden poetsen");
        tasks.add(task1);
    }

    public Task findTask(int id){
        for (Task task : tasks) {
            if (task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public void editTask(Task task){
        for (Task t:tasks) {
            if (task.getId()==t.getId()){
                int index = tasks.indexOf(t);
                tasks.get(index).setTitle(task.getTitle());
                tasks.get(index).setDescription(task.getDescription());
                tasks.get(index).setDate(task.getDate());
            }
        }
    }


    public void addTask(Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void addSubtasktoTask(CreateSubTaskDTO createSubTaskDTO) {
        SubTask sub = new SubTask(createSubTaskDTO.getTitle(), createSubTaskDTO.getDescription());
        for (Task task: tasks){
            if (task.getId() == createSubTaskDTO.getId()) {
                task.addSubTask(sub);
            }
        }
    }
}
