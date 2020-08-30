package com.taskmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubTask {
    @Id
    @GeneratedValue
    private long id;

    private String title, description;
    private long task;




    //Default constructor
    public SubTask() { };

    public SubTask(String title, String description){
        setDescription(description);
        setTitle(title);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString(){
        return title + " " + description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTask() {
        return task;
    }

    public void setTask(long task) {
        this.task = task;
    }


}
