package com.josias.planit.application.commands;

import java.time.LocalDateTime;

public record CreateTaskCommand(
        String title,
        String description,
        String priority,
        LocalDateTime dueDate
) {}
