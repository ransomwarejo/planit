package com.josias.planit.infrastructure.web.dto.response;


import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        String title,
        String description,
        String status,
        String priority,
        LocalDateTime dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

