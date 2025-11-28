package com.josias.planit.domain.vo;

import com.josias.planit.domain.exception.DomainException;

import java.time.LocalDate;

public class DueDate {

    private final LocalDate value;

    public DueDate(LocalDate value) {
        if (value== null || value.toString().trim().isEmpty()) {
            throw new DomainException("Due date cannot be null");
        }
        if (value.isBefore(LocalDate.now())) {
            throw new DomainException("Due date cannot be in the past");
        }
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DueDate dueDate = (DueDate) o;
        return value.equals(dueDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
