package com.josias.planit.application.commands;

import java.time.LocalDateTime;

public record UpdateTaskCommand(
        String title,
        String description,
        String status,
        String priority,
        LocalDateTime dueDate
) {}

