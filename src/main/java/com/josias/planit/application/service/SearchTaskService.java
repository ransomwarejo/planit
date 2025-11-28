package com.josias.planit.application.service;


import com.josias.planit.application.dto.request.TaskSearchCriteria;
import com.josias.planit.application.dto.response.TaskResponse;
import com.josias.planit.application.mapper.TaskMapper;
import com.josias.planit.domain.model.Task;
import com.josias.planit.domain.port.in.SearchTaskUseCase;
import com.josias.planit.domain.port.out.TaskQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class SearchTaskService implements SearchTaskUseCase {

    private final TaskQueryPort taskQueryPort;

    @Override
    public Page<TaskResponse> search(TaskSearchCriteria criteria, Pageable pageable) {
        Page<Task> page = taskQueryPort.search(criteria, pageable);
        return page.map(TaskMapper::toResponse);
    }
}
