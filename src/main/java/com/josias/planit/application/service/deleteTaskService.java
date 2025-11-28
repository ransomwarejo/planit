package com.josias.planit.application.service;
import com.josias.planit.domain.port.in.DeleteTaskUseCase;
import com.josias.planit.domain.port.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class deleteTaskService implements  DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    @Transactional
    public void delete(Long taskId) {
        taskRepositoryPort.deleteById(taskId);
    }
}
