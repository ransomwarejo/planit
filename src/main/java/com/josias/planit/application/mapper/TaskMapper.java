package com.josias.planit.application.mapper;

import com.josias.planit.application.dto.request.TaskRequest;
import com.josias.planit.application.dto.response.TaskResponse;
import com.josias.planit.domain.model.Task;

public class TaskMapper {


    public static Task toDomain(TaskRequest request){
        if (request == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        return task;
    }

    public static TaskResponse toResponse(Task task){
        if (task == null) {
            return null;
        }
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreateAt())
                .updatedAt(task.getUpdateAt())
                .build();
    }
}
