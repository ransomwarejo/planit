package com.josias.planit.application.port.in;

import com.josias.planit.application.commands.UpdateTaskCommand;

import java.util.UUID;

public interface UpdateTaskUseCase {
    void update(UUID id, UpdateTaskCommand command);
}
