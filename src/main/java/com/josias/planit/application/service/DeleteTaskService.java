package com.josias.planit.application.service;
import com.josias.planit.application.port.in.DeleteTaskUseCase;
import com.josias.planit.application.port.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTaskService implements  DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    @Transactional
    public void delete(UUID taskId) {
        taskRepositoryPort.deleteById(taskId);
    }
}
