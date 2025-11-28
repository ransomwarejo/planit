package com.josias.planit.domain.vo;

import com.josias.planit.domain.exception.DomainException;

public record TaskTitle(String value) {
    public TaskTitle {
        if (value == null || value.isBlank()) {
            throw new DomainException("Task title cannot be empty");
        }
        if (value.length() > 200) {
            throw new DomainException("Task title is too long");
        }
    }
}
