package com.josias.planit.infrastructure.mapper;

import com.josias.planit.domain.model.Task;
import com.josias.planit.domain.model.TaskPriority;
import com.josias.planit.domain.model.TaskStatus;
import com.josias.planit.domain.vo.DueDate;
import com.josias.planit.domain.vo.TaskDescription;
import com.josias.planit.domain.vo.TaskTitle;
import com.josias.planit.infrastructure.persistence.jpa.JpaTaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public JpaTaskEntity toJpa(Task task) {
        return new JpaTaskEntity(
                task.getId(),
                task.getTitle().value(),
                task.getDescription().value(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate().value(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public Task toDomain(JpaTaskEntity entity) {
        return Task.reconstruct(
                entity.getId(),
                new TaskTitle(entity.getTitle()),
                new TaskDescription(entity.getDescription()),
                TaskStatus.valueOf(entity.getStatus().name()),
                TaskPriority.valueOf(entity.getPriority().name()),
                new DueDate(entity.getDueDate()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
