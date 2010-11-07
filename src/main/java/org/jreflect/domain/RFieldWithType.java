package org.jreflect.domain;

import org.jreflect.exception.StackTraces;

public class RFieldWithType<T> {
    private final String name;

    public RFieldWithType(final String name) {
        this.name = name;
    }

    public RFieldWithTargetAndType<T> in(final Class<?> targetClass) {
        return new RFieldWithTargetAndType<T>(name, targetClass);
    }

    public RFieldWithTargetAndType<T> in(final Object targetObject) {
        try {
            if (targetObject instanceof Class<?>) {
                return in((Class<?>) targetObject);
            } else {
                return new RFieldWithTargetAndType<T>(name, targetObject);
            }
        } finally {
            StackTraces.store("in");
        }
    }
}
