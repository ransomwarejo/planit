package com.josias.planit.domain.port.in;

import com.josias.planit.application.dto.request.TaskSearchCriteria;
import com.josias.planit.application.dto.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchTaskUseCase {
    Page<TaskResponse> search(TaskSearchCriteria criteria, Pageable pageable);
}
