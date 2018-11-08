package com.json.ignore.filter;

import java.io.Serializable;
import java.util.*;

public class FilterFields implements Serializable {
    private static final long serialVersionUID = -2354837314560228182L;

    private final Map<Class, List<String>> fieldsMap;

    public FilterFields() {
        this.fieldsMap = new HashMap<>();
    }

    public FilterFields(Class className, List<String> fields) {
        this();
        this.fieldsMap.put(className, fields);
    }

    public Map<Class, List<String>> getFieldsMap() {
        return fieldsMap;
    }

    public List<String> getFields(Class className) {
        return fieldsMap.containsKey(className) ? fieldsMap.get(className) : new ArrayList<>();
    }

    public FilterFields appendToMap(Class classname, List<String> value) {
        List<String> fields = fieldsMap.computeIfAbsent(classname, k -> new ArrayList<>());
        value.forEach(v -> {
            if (!fields.contains(v)) fields.add(v);
        });

        return this;
    }
}