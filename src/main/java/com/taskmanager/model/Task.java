package com.taskmanager.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String title, description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime Date;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<SubTask> subtasks = new ArrayList<>();


    //Formatter for return
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("': due ' MMMM d',' YYYY 'at' h:mm a").withLocale(Locale.US);

    //Default constructor
    public Task() { };
    public Task(String title, LocalDateTime Date, String description) {
        setTitle(title);
        setDescription(description);
        setDate(Date);
    }

    public void addSubTask(SubTask sub) {
        if(sub == null) {
            throw new IllegalArgumentException("sub is null");
        }
        subtasks.add(sub);
    }



    /******************
     GETTERS AND SETTERS
     ******************/
    public String getTaskFull() {
        return "(id: " + getId() +") " + getTitle() + getDateFormatted() ;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public String getDateFormatted() {
        return formatter.format(this.Date);
    }

    public LocalDateTime getDate() {
        return Date;
    }


    public void setTitle(String title) {
        if(title == null || title.trim().equals("")) {
            throw new IllegalArgumentException("title invalid");
        }
        this.title = title;
    }

    public void setDescription(String description) {
        if(description == null || description.trim().equals("")) {
            throw new IllegalArgumentException("description invalid");
        }
        this.description = description;
    }

    public void setDate(LocalDateTime Date) {
        if(Date != null) {
            this.Date = Date;
        } else {
            throw new IllegalArgumentException("date invalid");
        }
    }
    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<SubTask> getSubTasks() {
        return subtasks;
    }
    public void setSubtasks(List<SubTask> subTasks) {
        this.subtasks = subTasks;

    }


}
