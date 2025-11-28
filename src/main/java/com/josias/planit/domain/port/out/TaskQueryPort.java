package com.josias.planit.domain.port.out;

import com.josias.planit.application.dto.request.TaskSearchCriteria;
import com.josias.planit.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskQueryPort {
    Page<Task> search(TaskSearchCriteria criteria, Pageable pageable);;
}
