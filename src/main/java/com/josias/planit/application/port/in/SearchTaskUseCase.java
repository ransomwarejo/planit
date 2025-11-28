package com.josias.planit.application.port.in;

import com.josias.planit.infrastructure.web.dto.request.TaskSearchCriteria;
import com.josias.planit.infrastructure.web.dto.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchTaskUseCase {
    Page<TaskResponse> search(TaskSearchCriteria criteria, Pageable pageable);
}
