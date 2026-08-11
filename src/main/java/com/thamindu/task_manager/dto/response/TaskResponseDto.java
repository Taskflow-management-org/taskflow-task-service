package com.thamindu.task_manager.dto.response;


import com.thamindu.task_manager.enums.TaskPriority;
import com.thamindu.task_manager.enums.TaskStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TaskResponseDto(
    UUID id,
    String title,
    String description,
    TaskStatus status,
    TaskPriority priority,
    UUID assignedUserId,
    UUID createdByUserId,
    LocalDateTime dueDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}