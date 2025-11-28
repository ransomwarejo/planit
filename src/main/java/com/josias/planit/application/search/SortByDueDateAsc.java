package com.josias.planit.application.search;

import org.springframework.data.domain.Sort;

public class SortByDueDateAsc implements SortStrategy{

    @Override
    public Sort toSort() {
        return Sort.by(Sort.Direction.ASC, "dueDate");
    }

    @Override
    public String name() {
        return "dueDateAsc";
    }
}
