package com.josias.planit.application.service;
import com.josias.planit.application.commands.CreateTaskCommand;
import com.josias.planit.domain.model.TaskPriority;
import com.josias.planit.domain.vo.DueDate;
import com.josias.planit.domain.vo.TaskDescription;
import com.josias.planit.domain.vo.TaskTitle;
import com.josias.planit.domain.model.Task;
import com.josias.planit.application.port.in.CreateTaskUseCase;
import com.josias.planit.application.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public CreateTaskService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public UUID create(CreateTaskCommand cmd) {
        Task task = Task.create(
                new TaskTitle(cmd.title()),
                new TaskDescription(cmd.description()),
                TaskPriority.valueOf(cmd.priority()),
                new DueDate(cmd.dueDate())
        );
        taskRepository.save(task);
        return task.getId();
    }
}
