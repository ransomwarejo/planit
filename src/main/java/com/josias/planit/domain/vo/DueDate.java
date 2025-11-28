package com.josias.planit.domain.vo;

import com.josias.planit.domain.exception.DomainException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DueDate(LocalDateTime value) {
    public DueDate {
        if (value == null) {
            throw new DomainException("Due date cannot be null");
        }
    }
    public boolean isPast() {
        return value.isBefore(LocalDateTime.now());
    }
}
