package com.josias.planit.infrastructure.web.dto.request;

import com.josias.planit.domain.model.TaskPriority;
import com.josias.planit.domain.model.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskSearchCriteria {
    private String titleContains;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDateFrom;
    private LocalDate dueDateTo;
    private String sortBy;
    private String sortDirection;
}
