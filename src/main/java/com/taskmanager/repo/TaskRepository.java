package com.taskmanager.repo;

import com.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> list;

    public TaskRepository() {
        list = new ArrayList<>();
        list.add(new Task("Task 1", LocalDateTime.of(2002, 7, 3, 13, 2), "Cleaning"));
        list.add(new Task("Task 2", LocalDateTime.of(2005, 12, 13, 3, 2),"Studying"));
    }

    public List<Task> getTasks() {
        return list;
    }


    public void addTask(Task task) {
        list.add(task);
    }

    public void addTask(int index, Task task) {
        list.remove(index);
        list.add(index,task);
    }


}
