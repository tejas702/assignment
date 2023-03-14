package com.taskmanager.demo.services;

import com.taskmanager.demo.entities.TaskEntity;
import com.taskmanager.demo.entities.UserEntity;
import com.taskmanager.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity addUsers() {
        UserEntity user = UserEntity.builder().build();
        try{
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            log.warn("error saving user to users table", e);
        }
        return user;
    }

    public List<Integer> getTasks(Integer userId) {
        if(!isUserPresent(userId)) return null;
        List<Integer> taskEntityList = null;
        try{
            taskEntityList = userRepository.getTasksList(userId);
        } catch(Exception e) {
            log.warn("error fetching tasks list for userid: " + userId, e);
        }
        return taskEntityList;
    }

    public boolean isUserPresent(Integer userId) {
        List<UserEntity> totalUsers = userRepository.findAll();
        if(totalUsers.isEmpty()) return false; // no user exists
        return totalUsers.stream().anyMatch(x -> x.getUserid().equals(userId)); // user is not present
    }
}
