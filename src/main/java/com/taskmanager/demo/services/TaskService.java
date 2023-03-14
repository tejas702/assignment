package com.taskmanager.demo.services;

import com.taskmanager.demo.entities.TaskEntity;
import com.taskmanager.demo.entities.UserEntity;
import com.taskmanager.demo.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TaskService {

    private String SUCCESS = "SUCCESS";

    private String FAILURE = "FAILURE";

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    public List<Integer> addTask(Integer userId) {
        if(!userService.isUserPresent(userId)) return null; // if user does not exist, return null;
        UserEntity user = UserEntity.builder().userid(userId).build();
        TaskEntity task = TaskEntity.builder().userid(user).build();
        try{
            taskRepository.saveAndFlush(task);
        } catch(Exception e) {
            log.warn("error saving task with userid: " + userId,e); // userId does not exist
        }
        if(Objects.isNull(task.getTaskid())) return null;
        return userService.getTasks(userId); // return all tasks of the userId
    }

    public void deleteTask(Integer taskId) {
        try{
            taskRepository.deleteTaskById(taskId);
        } catch (Exception e) {
            log.error("error deleting task with task id: " + taskId, e);
        }
    }

    public List<Integer> updateTask(Integer taskId, Integer userId) {
        if(!isTaskPresent(taskId) || !userService.isUserPresent(userId)) return null;
        UserEntity user = UserEntity.builder().userid(userId).build();
        TaskEntity task = TaskEntity.builder().taskid(taskId).userid(user).build();
        try{
            taskRepository.saveAndFlush(task);
        } catch(Exception e) {
            log.warn("error updating task with taskId " + taskId  + " and userId " + userId, e);
        }
        return userService.getTasks(userId);
    }

    public boolean isTaskPresent(Integer taskId) {
        List<TaskEntity> totalTasks = taskRepository.findAll();
        if(totalTasks.isEmpty()) return false; // no task exists
        return totalTasks.stream().anyMatch(x -> x.getTaskid().equals(taskId)); // task is not present
    }
}
