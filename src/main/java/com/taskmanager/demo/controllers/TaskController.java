package com.taskmanager.demo.controllers;

import com.taskmanager.demo.dtos.TaskDto;
import com.taskmanager.demo.dtos.UpdateTaskRequestDto;
import com.taskmanager.demo.dtos.UserDto;
import com.taskmanager.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(allowedHeaders = {"*", "Content-Type", "token", "authorization", "content-type", "accept", "Content type"}, origins = "*", originPatterns = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public List<String> addTask(@RequestBody UserDto userDto) {
        return taskService.addTask(userDto);
    }

    @DeleteMapping("/delete")
    public String deleteTask(@RequestBody TaskDto taskDto) {
        return taskService.deleteTask(taskDto.getTaskId());
    }

    @PutMapping("/update")
    public List<String> updateTask(@RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        return taskService.updateTask(updateTaskRequestDto);
    }
}
