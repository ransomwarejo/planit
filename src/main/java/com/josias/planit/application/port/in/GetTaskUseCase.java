package com.josias.planit.application.port.in;

import com.josias.planit.domain.model.Task;
import com.josias.planit.infrastructure.web.dto.response.TaskResponse;

import java.util.UUID;

public interface GetTaskUseCase {
    Task getById(UUID id);
}
