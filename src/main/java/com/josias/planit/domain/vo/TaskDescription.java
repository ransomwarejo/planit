package com.josias.planit.domain.vo;

import com.josias.planit.domain.exception.DomainException;

public class TaskDescription {

    private final String value;

    public TaskDescription(String value) {
        if (value != null && value.length() > 500) {
            throw new DomainException("Task description cannot exceed 500 characters");
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
        TaskDescription that = (TaskDescription) o;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
