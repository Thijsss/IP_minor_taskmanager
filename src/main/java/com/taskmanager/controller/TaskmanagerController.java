package com.taskmanager.controller;

import com.taskmanager.dto.TaskDTO;
import com.taskmanager.model.*;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskmanagerController {
    @Autowired
    private TaskService service;
    @RequestMapping("/tasks")
    public String getTasks(Model model){
        model.addAttribute("tasks", service.getAllTasks());
        return "tasks";
    }

    @RequestMapping("/")
    public String getHome(){
        return "index";
    }

    @RequestMapping("/tasks/{id}")
    public String getTask(Model model, @PathVariable("id") int id){
        Task foundTask = service.getTaskById(id);
        model.addAttribute("task", foundTask);
        return "task";

    }

    @GetMapping("/tasks/new")
        public String newTask(){
        return "form";
    }

    @PostMapping("/tasks/new")
    public String submitTask(@ModelAttribute TaskDTO taskDTO){
        service.addTask(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(Model model, @PathVariable("id") long id){
        Task foundTask = service.getTaskById(id);
        model.addAttribute("task", foundTask);
        return "edit";
    }

    @PostMapping("/tasks/edit")
    public String submitEdit(@ModelAttribute Task task){
        service.editTask(task);
        return "redirect:/tasks/"+task.getId();
    }

    @GetMapping("/tasks/{id}/sub/create")
    public ModelAndView newSubTask(Model model, @PathVariable("id") long id){
        ModelAndView a = new ModelAndView("createSubTask");
        a.addObject("task", service.getTaskById(id));
        return a;
       // Task foundTask = service.getTaskById(id);
        //model.addAttribute("task", foundTask);
        //return "createSubTask";
    }

    @PostMapping("/tasks/createSubTask")
    public String createSubTask(@ModelAttribute SubTask subTask){
        service.addSubTask(subTask);
        return "redirect:/tasks/"+subTask.getTask();
    }
}
