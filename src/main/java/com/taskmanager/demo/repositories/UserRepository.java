package com.taskmanager.demo.repositories;

import com.taskmanager.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM tasks t JOIN users u ON t.userid = u.userid WHERE u.userid = :userId", nativeQuery = true)
    List<Integer> getTasksList(@Param("userId") Integer userId) throws Exception;

}
