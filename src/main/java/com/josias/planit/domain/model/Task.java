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


    private Task(UUID uuid, TaskTitle title, TaskDescription description,
                 TaskStatus status,TaskPriority priority, DueDate dueDate,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt
                 ) {

        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.title = Objects.requireNonNull(title);
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.updatedAt = updatedAt == null ? LocalDateTime.now() : updatedAt;


    }

    // ---------- FACTORY METHOD : Création ----------

    public static Task create(TaskTitle title, TaskDescription description,
                              TaskStatus status, TaskPriority priority,
                              DueDate dueDate) {
        LocalDateTime now = LocalDateTime.now();
        return new Task(null, title, description, TaskStatus.PENDING, priority, dueDate,
                now, now);
    }

    // ---------- FACTORY METHOD : Reconstruction ----------
    public static Task reconstruct(UUID uuid, TaskTitle title, TaskDescription description,
                                   TaskStatus status, TaskPriority priority,
                                   DueDate dueDate,
                                   LocalDateTime createdAt,
                                   LocalDateTime updatedAt) {
        return new Task(uuid, title, description, status, priority, dueDate,
                createdAt, updatedAt);
    }




    public UUID getId() {
        return uuid;
    }

    public TaskTitle getTitle() {
        return title;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }
    public DueDate getDueDate() {
        return dueDate;
    }
    public LocalDateTime getCreateAt() {
        return createdAt;
    }
    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }


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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + uuid +
                ", title=" + title.getValue() +
                ", description=" + description.getValue() +
                ", status=" + status +
                ", priority=" + priority +
                ", dueDate=" + dueDate.getValue() +
                ", createAt=" + createdAt +
                ", updateAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(uuid, task.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }


    public void markAsCompleted() {
       changeStatus(TaskStatus.COMPLETED);
    }

    private void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }


}
