package com.josias.planit.application.service;
import com.josias.planit.application.dto.request.TaskRequest;
import com.josias.planit.application.dto.response.TaskResponse;
import com.josias.planit.application.mapper.TaskMapper;
import com.josias.planit.domain.model.Task;
import com.josias.planit.domain.port.in.CreateTaskUseCase;
import com.josias.planit.domain.port.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class createTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    @Transactional
    public TaskResponse create(TaskRequest request) {

        Task task = TaskMapper.toDomain(request);
        Task savedTask = taskRepositoryPort.save(task);
        return TaskMapper.toResponse(savedTask);

    }

}
