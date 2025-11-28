package com.josias.planit.application.port.out;

import com.josias.planit.infrastructure.web.dto.request.TaskSearchCriteria;
import com.josias.planit.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskQueryPort {
    Page<Task> search(TaskSearchCriteria criteria, Pageable pageable);;
}
