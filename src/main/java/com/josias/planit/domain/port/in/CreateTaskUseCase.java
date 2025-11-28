package com.josias.planit.domain.port.in;

import com.josias.planit.application.dto.request.TaskRequest;
import com.josias.planit.application.dto.response.TaskResponse;

public interface CreateTaskUseCase {
    TaskResponse create(TaskRequest request);
}
