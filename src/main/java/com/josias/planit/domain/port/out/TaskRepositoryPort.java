package com.josias.planit.domain.port.out;

import com.josias.planit.domain.model.Task;

import java.util.Optional;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(Long id);
    void deleteById(Long id);
}
