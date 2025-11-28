package com.josias.planit.infrastructure.web;


import com.josias.planit.application.dto.request.TaskRequest;
import com.josias.planit.application.dto.request.TaskSearchCriteria;
import com.josias.planit.application.dto.response.TaskResponse;
import com.josias.planit.domain.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createTask.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getTask.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody TaskRequest request) {
        return ResponseEntity.ok(updateTask.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
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
