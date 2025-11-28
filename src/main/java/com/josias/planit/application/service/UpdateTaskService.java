package com.josias.planit.application.service;


import com.josias.planit.application.commands.UpdateTaskCommand;
import com.josias.planit.domain.exception.DomainException;
import com.josias.planit.domain.model.TaskPriority;
import com.josias.planit.domain.model.TaskStatus;
import com.josias.planit.domain.vo.DueDate;
import com.josias.planit.domain.vo.TaskDescription;
import com.josias.planit.domain.vo.TaskTitle;
import com.josias.planit.domain.model.Task;
import com.josias.planit.application.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;
import com.josias.planit.application.port.in.UpdateTaskUseCase;

import java.util.UUID;

@Service
public class UpdateTaskService implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public UpdateTaskService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void update(UUID id, UpdateTaskCommand cmd) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new DomainException("Task not found"));

        if (cmd.title() != null) task.changeTitle(new TaskTitle(cmd.title()));
        if (cmd.description() != null) task.changeDescription(new TaskDescription(cmd.description()));
        if (cmd.priority() != null) task.changePriority(TaskPriority.valueOf(cmd.priority()));
        if (cmd.status() != null) task.changeStatus(TaskStatus.valueOf(cmd.status()));
        if (cmd.dueDate() != null) task.changeDueDate(new DueDate(cmd.dueDate()));

        taskRepository.save(task);
    }
}
