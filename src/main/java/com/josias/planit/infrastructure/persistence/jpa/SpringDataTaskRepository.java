package com.josias.planit.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringDataTaskRepository extends JpaRepository<JpaTaskEntity, Long> , JpaSpecificationExecutor<JpaTaskEntity> {

}
