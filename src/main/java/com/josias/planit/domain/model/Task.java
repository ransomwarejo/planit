package com.josias.planit.domain.model;


import com.josias.planit.domain.exception.DomainException;
import com.josias.planit.domain.vo.DueDate;
import com.josias.planit.domain.vo.TaskDescription;
import com.josias.planit.domain.vo.TaskTitle;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private final UUID uuid;
    private TaskTitle title;
    private TaskDescription description;
    private TaskStatus status;
    private TaskPriority priority;
    private DueDate dueDate;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- Private Constructor ---
    private Task(
            UUID uuid,
            TaskTitle title,
            TaskDescription description,
            TaskStatus status,
            TaskPriority priority,
            DueDate dueDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
        this.uuid = uuid != null ? uuid : UUID.randomUUID();
        this.title = Objects.requireNonNull(title);
        this.description = Objects.requireNonNull(description);
        this.status = Objects.requireNonNull(status);
        this.priority = Objects.requireNonNull(priority);
        this.dueDate = Objects.requireNonNull(dueDate);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        validate();
    }

    // ---------- Factory : Creation ----------
    public static Task create(
            TaskTitle title,
            TaskDescription description,
            TaskPriority priority,
            DueDate dueDate
    ){
        LocalDateTime now = LocalDateTime.now();
        return new Task(
                null,
                title,
                description,
                TaskStatus.PENDING,
                priority != null ? priority : TaskPriority.LOW,
                dueDate,
                now,
                now
        );
    }

    // ---------- Factory : Reconstitution ----------
    public static Task reconstruct(
            UUID uuid,
            TaskTitle title,
            TaskDescription description,
            TaskStatus status,
            TaskPriority priority,
            DueDate dueDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
        return new Task(
                uuid,
                title,
                description,
                status,
                priority,
                dueDate,
                createdAt,
                updatedAt
        );
    }

    // ------ Domain Methods -------
    public void changeTitle(TaskTitle title) {
        this.title = title;
        updateTimestamp();
    }

    public void changeDescription(TaskDescription description) {
        this.description = description;
        updateTimestamp();
    }

    public void changeStatus(TaskStatus status) {
        if (!this.status.isModifiable()) {
            throw new DomainException("Task is already completed");
        }
        this.status = status;
        updateTimestamp();
    }

    public void changePriority(TaskPriority priority) {
        this.priority = priority;
        updateTimestamp();
    }

    public void changeDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
        updateTimestamp();
    }

    public void markAsCompleted() {
        changeStatus(TaskStatus.COMPLETED);
    }

    private void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    // --- Invariant Validation ---
    private void validate() {
        if (dueDate.isPast()) {
            throw new DomainException("Due date cannot be in the past");
        }
    }

    // --- Getters ---
    public UUID getId() { return uuid; }
    public TaskTitle getTitle() { return title; }
    public TaskDescription getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public TaskPriority getPriority() { return priority; }
    public DueDate getDueDate() { return dueDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
