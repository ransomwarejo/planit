package com.josias.planit.application.search;

import java.util.Map;
import java.util.HashMap;

public class SortStrategyFactory {
    private final Map<String, SortStrategy> strategies = new HashMap<>();

    public SortStrategyFactory(){
        registerStrategy(new SortByDueDateAsc());
    }

    public void registerStrategy(SortStrategy strategy){
        strategies.put(strategy.name(), strategy);
    }

    public SortStrategy getStrategy(String name){
        return strategies.get(name);
    }

}
