package com.josias.planit.infrastructure.persistence.adapter;


import com.josias.planit.application.dto.request.TaskSearchCriteria;
import com.josias.planit.domain.model.Task;
import com.josias.planit.domain.port.out.TaskQueryPort;
import com.josias.planit.domain.port.out.TaskRepositoryPort;
import com.josias.planit.infrastructure.persistence.jpa.JpaTaskEntity;
import com.josias.planit.infrastructure.persistence.jpa.SpringDataTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort, TaskQueryPort {

    private final SpringDataTaskRepository jpa;

    @Override
    public Task save(Task task) {
        JpaTaskEntity e = JpaTaskEntity.fromDomain(task);
        JpaTaskEntity saved = jpa.save(e);
        return saved.toDomain();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpa.findById(id).map(JpaTaskEntity::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Page<Task> search(TaskSearchCriteria criteria, Pageable pageable) {
        Specification<JpaTaskEntity> spec = buildSpecification(criteria);
        return jpa.findAll(spec, pageable).map(JpaTaskEntity::toDomain);
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
