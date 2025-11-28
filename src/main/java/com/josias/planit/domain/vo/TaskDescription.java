package com.josias.planit.domain.vo;

import com.josias.planit.domain.exception.DomainException;

public record TaskDescription(String value) {
    public TaskDescription {
        if (value == null || value.isBlank()) {
            throw new DomainException("Task description cannot be empty");
        }
    }
}

