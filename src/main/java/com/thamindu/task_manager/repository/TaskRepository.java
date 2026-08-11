package com.thamindu.task_manager.repository;

import com.thamindu.task_manager.entity.Task;
import com.thamindu.task_manager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;


public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByAssignedUserId(UUID assignedUserId);

    List<Task> findByStatus(TaskStatus status);

}
