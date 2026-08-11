package com.thamindu.task_manager.dto.request;

import com.thamindu.task_manager.enums.TaskPriority;
import com.thamindu.task_manager.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequestDto(
    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must not exceed 150 characters")
    String title,

    String description,

    @NotNull(message = "Status is required")
    TaskStatus status,

    @NotNull(message = "Priority is required")
    TaskPriority priority,

    UUID assignedUserId,
    LocalDateTime dueDate
) {}