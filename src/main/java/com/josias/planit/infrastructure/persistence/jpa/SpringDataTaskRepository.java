package com.josias.planit.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SpringDataTaskRepository extends JpaRepository<JpaTaskEntity, UUID> , JpaSpecificationExecutor<JpaTaskEntity> {

}
