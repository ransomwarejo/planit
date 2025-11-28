package com.josias.planit.infrastructure.persistence.adapter;


import com.josias.planit.infrastructure.mapper.TaskMapper;
import com.josias.planit.infrastructure.web.dto.request.TaskSearchCriteria;
import com.josias.planit.domain.model.Task;
import com.josias.planit.application.port.out.TaskQueryPort;
import com.josias.planit.application.port.out.TaskRepositoryPort;
import com.josias.planit.infrastructure.persistence.jpa.JpaTaskEntity;
import com.josias.planit.infrastructure.persistence.jpa.SpringDataTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort, TaskQueryPort {

    private final SpringDataTaskRepository jpa;
    private final TaskMapper mapper;

    @Override
    public Task save(Task task) {
        JpaTaskEntity entity = mapper.toJpa(task);
        return mapper.toDomain(jpa.save(entity));
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public Page<Task> search(TaskSearchCriteria criteria, Pageable pageable) {
        Specification<JpaTaskEntity> spec = buildSpecification(criteria);
        return jpa.findAll(spec, pageable).map(mapper::toDomain);
    }

    private Specification<JpaTaskEntity> buildSpecification(TaskSearchCriteria c) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (c.getTitleContains() != null && !c.getTitleContains().isEmpty()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("title")), "%" + c.getTitleContains().toLowerCase() + "%"));
            }
            if (c.getStatus() != null) {
                predicates = cb.and(predicates, cb.equal(root.get("status"), c.getStatus()));
            }
            if (c.getPriority() != null) {
                predicates = cb.and(predicates, cb.equal(root.get("priority"), c.getPriority()));
            }
            if (c.getDueDateFrom() != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("dueDate"), c.getDueDateFrom()));
            }
            if (c.getDueDateTo() != null) {
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("dueDate"), c.getDueDateTo()));
            }
            return predicates;
        };
    }
}
