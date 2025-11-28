package com.josias.planit.application.port.out;

import com.josias.planit.domain.model.Task;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    void deleteById(UUID id);
}
