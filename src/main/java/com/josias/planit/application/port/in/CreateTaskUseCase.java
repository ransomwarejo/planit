package com.josias.planit.application.port.in;

import com.josias.planit.application.commands.CreateTaskCommand;

import java.util.UUID;

public interface CreateTaskUseCase {
    UUID create(CreateTaskCommand command);
}
