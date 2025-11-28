package com.josias.planit.domain.model;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    public boolean isModifiable() {
        return this != COMPLETED && this != CANCELLED;
    }
}
