package com.thamindu.task_manager.controller;

import com.thamindu.task_manager.dto.request.CreateTaskRequestDto;
import com.thamindu.task_manager.dto.response.TaskResponseDto;
import com.thamindu.task_manager.enums.TaskStatus;
import com.thamindu.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @RequestHeader("X-User-Id") String currentUserId,
            @Valid @RequestBody CreateTaskRequestDto request
            )
    {
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(taskService.createTask(request,currentUserId));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/find-by-assign-user/{id}")
    public ResponseEntity<List<TaskResponseDto>> getTaskByAssignUser(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getTaskByAssignUser(id));
    }

    @GetMapping("/find-by-status")
    public ResponseEntity<List<TaskResponseDto>> getTaskByStatus(@RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.getTaskByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @PathVariable UUID id,
            @RequestParam TaskStatus status
    )
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.updateTaskStatus(id,status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> getTaskByStatus(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
