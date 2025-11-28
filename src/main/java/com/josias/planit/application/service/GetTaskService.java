package com.josias.planit.application.service;

import com.josias.planit.application.dto.response.TaskResponse;
import com.josias.planit.application.mapper.TaskMapper;
import com.josias.planit.domain.model.Task;
import com.josias.planit.domain.port.in.GetTaskUseCase;
import com.josias.planit.domain.port.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskService implements GetTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public TaskResponse getById(Long id) {
        Task task = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return TaskMapper.toResponse(task);
    }

}
