package com.josias.planit.application.service;


import com.josias.planit.application.dto.request.TaskRequest;
import com.josias.planit.application.dto.response.TaskResponse;
import com.josias.planit.application.mapper.TaskMapper;
import com.josias.planit.domain.model.Task;
import com.josias.planit.domain.port.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.josias.planit.domain.port.in.UpdateTaskUseCase;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class updateTaskService implements  UpdateTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    @Transactional
    public TaskResponse update(Long id, TaskRequest request){
        Task existingTask = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setStatus(request.getStatus());
        existingTask.setPriority(request.getPriority());
        existingTask.setDueDate(request.getDueDate());


        Task updatedTask = taskRepositoryPort.save(existingTask);
        return TaskMapper.toResponse(updatedTask);
    }
}
