package com.josias.planit.infrastructure.web;


import com.josias.planit.application.commands.CreateTaskCommand;
import com.josias.planit.application.commands.UpdateTaskCommand;
import com.josias.planit.domain.model.Task;
import com.josias.planit.infrastructure.web.dto.request.CreateTaskRequest;
import com.josias.planit.infrastructure.web.dto.request.TaskSearchCriteria;
import com.josias.planit.infrastructure.web.dto.request.UpdateTaskRequest;
import com.josias.planit.infrastructure.web.dto.response.TaskResponse;
import com.josias.planit.application.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTask;
    private final UpdateTaskUseCase updateTask;
    private final GetTaskUseCase getTask;
    private final DeleteTaskUseCase deleteTask;
    private final SearchTaskUseCase searchTask;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody CreateTaskRequest request) {
        UUID id = createTask.create(
                new CreateTaskCommand(
                        request.title(),
                        request.description(),
                        request.priority(),
                        request.dueDate()
                )
        );
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable UUID id) {
       Task task = getTask.getById(id);
      return ResponseEntity.ok(new TaskResponse(
              task.getId(),
              task.getTitle().value(),
              task.getDescription().value(),
              task.getStatus().name(),
              task.getPriority().name(),
              task.getDueDate().value(),
              task.getCreatedAt(),
              task.getUpdatedAt()
      ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        updateTask.update(id, new UpdateTaskCommand(
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                request.dueDate()
        ));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteTask.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> search(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        TaskSearchCriteria criteria = new TaskSearchCriteria();
        criteria.setTitleContains(q);
        PageRequest pageable = PageRequest.of(page, size);
        Page<TaskResponse> results = searchTask.search(criteria, pageable);
        return ResponseEntity.ok(results);
    }


}
