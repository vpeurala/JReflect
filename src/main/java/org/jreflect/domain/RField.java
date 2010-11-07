package org.jreflect.domain;

import org.jreflect.exception.StackTraces;

public class RField {
    private final String name;

    public RField(final String name) {
        this.name = name;
    }

    public RFieldWithTarget in(final Class<?> targetClass) {
        return new RFieldWithTarget(name, targetClass);
    }

    public RFieldWithTarget in(final Object targetObject) {
        try {
            if (targetObject instanceof Class<?>) {
                return in((Class<?>) targetObject);
            } else {
                return new RFieldWithTarget(name, targetObject);
            }
        } finally {
            StackTraces.store("in");
        }
    }

    public <T> RFieldWithType<T> ofType(
            @SuppressWarnings("unused") final Class<T> type) {
        return new RFieldWithType<T>(name);
    }
}
