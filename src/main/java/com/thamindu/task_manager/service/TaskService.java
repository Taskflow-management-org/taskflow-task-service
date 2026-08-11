package com.thamindu.task_manager.service;

import com.thamindu.task_manager.dto.request.CreateTaskRequestDto;
import com.thamindu.task_manager.dto.response.TaskResponseDto;
import com.thamindu.task_manager.enums.TaskStatus;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(CreateTaskRequestDto request, String createByUserId);
    TaskResponseDto getTaskById(UUID id);
    List<TaskResponseDto> getAllTasks();
    List<TaskResponseDto> getTaskByAssignUser(String userId);
    List<TaskResponseDto> getTaskByStatus(TaskStatus status);
    TaskResponseDto updateTaskStatus(UUID id,TaskStatus status);
    void deleteTask(UUID id);
}
