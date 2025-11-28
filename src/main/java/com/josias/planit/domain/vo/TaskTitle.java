package com.josias.planit.domain.vo;

import com.josias.planit.domain.exception.DomainException;

public class TaskTitle {

    private final String value;

    public TaskTitle(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("Task title cannot be null or empty");
        }
        if (value.length() > 100) {
            throw new DomainException("Task title cannot exceed 100 characters");
        }
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTitle taskTitle = (TaskTitle) o;
        return value.equals(taskTitle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
