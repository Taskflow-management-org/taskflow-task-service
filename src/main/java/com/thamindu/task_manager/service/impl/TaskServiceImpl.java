package com.thamindu.task_manager.service.impl;

import com.thamindu.task_manager.dto.request.CreateTaskRequestDto;
import com.thamindu.task_manager.dto.response.TaskResponseDto;
import com.thamindu.task_manager.entity.Task;
import com.thamindu.task_manager.enums.TaskStatus;
import com.thamindu.task_manager.exception.ResourceNotFoundException;
import com.thamindu.task_manager.mapper.ObjectMapper;
import com.thamindu.task_manager.repository.TaskRepository;
import com.thamindu.task_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public TaskResponseDto createTask(CreateTaskRequestDto request, String createByUserId) {
        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .priority(request.priority())
                .assignedUserId(request.assignedUserId())
                .createdUserId(UUID.fromString(createByUserId))
                .dueDate(request.dueDate())
                .build();
        Task saveTask = taskRepository.save(task);
        return objectMapper.mapToResponseDto(saveTask);

    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto getTaskById(UUID id) {
        Task selectedTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));

        return objectMapper.mapToResponseDto(selectedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(objectMapper::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTaskByAssignUser(String userId) {
        return taskRepository.findByAssignedUserId(UUID.fromString(userId))
                .stream()
                .map(objectMapper::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTaskByStatus(TaskStatus status) {
       return taskRepository.findByStatus(status)
               .stream()
               .map(objectMapper::mapToResponseDto)
               .toList();
    }

    @Override
    @Transactional
    public TaskResponseDto updateTaskStatus(UUID id, TaskStatus status) {
       Task selectedTask =  taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));

       selectedTask.setStatus(status);
       Task savedTask = taskRepository.save(selectedTask);

       return objectMapper.mapToResponseDto(savedTask);
    }

    @Override
    @Transactional
    public void deleteTask(UUID id) {
        Task selectedTask =  taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));
       taskRepository.delete(selectedTask);

    }
}
