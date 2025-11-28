package com.josias.planit.infrastructure.web.dto.request;

import java.time.LocalDateTime;

public record UpdateTaskRequest(
    String title,
    String description,
    String status,
    String priority,
    LocalDateTime dueDate
){}
