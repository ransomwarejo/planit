package com.josias.planit.domain.port.in;

import com.josias.planit.application.dto.response.TaskResponse;

public interface GetTaskUseCase {
    TaskResponse getById(Long id);
}
