package com.taskmanager.demo.controllers;

import com.taskmanager.demo.dtos.TaskDto;
import com.taskmanager.demo.dtos.UpdateTaskRequestDto;
import com.taskmanager.demo.dtos.UserDto;
import com.taskmanager.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public List<String> addTask(@RequestBody UserDto userDto) {
        List<Integer> taskList = taskService.addTask(userDto.getUserId());
        if(Objects.isNull(taskList)) return null;
        return taskList.stream().map(Object::toString).toList();
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestBody TaskDto taskDto) {
        taskService.deleteTask(taskDto.getTaskId());
    }

    @PutMapping("/update")
    public List<String> updateTask(@RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        List<Integer> taskList = taskService.updateTask(updateTaskRequestDto.getTaskId(), updateTaskRequestDto.getUserId());
        if(Objects.isNull(taskList)) return null;
        return taskList.stream().map(Object::toString).toList();
    }
}
