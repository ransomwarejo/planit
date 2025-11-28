package com.josias.planit.application.dto.request;

import com.josias.planit.domain.model.TaskPriority;
import com.josias.planit.domain.model.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
}
