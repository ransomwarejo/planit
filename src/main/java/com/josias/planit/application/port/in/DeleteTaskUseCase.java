package com.josias.planit.application.port.in;

import java.util.UUID;

public interface DeleteTaskUseCase {
    void delete(UUID id);
}
