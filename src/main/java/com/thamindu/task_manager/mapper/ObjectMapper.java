package com.thamindu.task_manager.mapper;

import com.thamindu.task_manager.dto.response.TaskResponseDto;
import com.thamindu.task_manager.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {
    public TaskResponseDto mapToResponseDto(Task task){
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .assignedUserId(task.getAssignedUserId())
                .createdByUserId(task.getCreatedUserId())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
