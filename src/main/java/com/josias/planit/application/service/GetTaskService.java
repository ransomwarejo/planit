package com.josias.planit.application.service;

import com.josias.planit.domain.exception.DomainException;
import com.josias.planit.infrastructure.web.dto.response.TaskResponse;
import com.josias.planit.infrastructure.mapper.TaskMapper;
import com.josias.planit.domain.model.Task;
import com.josias.planit.application.port.in.GetTaskUseCase;
import com.josias.planit.application.port.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTaskService implements GetTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public GetTaskService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new DomainException("Task not found"));
    }
}

