package com.taskmanager.demo.repositories;

import com.taskmanager.demo.entities.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks t WHERE t.taskid = :taskId", nativeQuery = true)
    void deleteTaskById(@Param("taskId")Integer taskId) throws Exception;
}
