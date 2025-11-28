package com.josias.planit.application.search;

import org.springframework.data.domain.Sort;

public interface SortStrategy {
    Sort toSort();
    String name();
}
