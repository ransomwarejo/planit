package com.josias.planit.infrastructure.web.dto.request;

import lombok.*;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        String title,
        String description,
        String priority,
        LocalDateTime dueDate
) {}
