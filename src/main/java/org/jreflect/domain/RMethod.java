package org.jreflect.domain;

import org.jreflect.exception.StackTraces;

public class RMethod {
    private final String methodName;

    public RMethod(final String name) {
        methodName = name;
    }

    public RMethodWithTarget in(final Class<?> targetClass) {
        try {
            return new RMethodWithTarget(methodName, targetClass);
        } finally {
            StackTraces.store("in");
        }
    }

    public RMethodWithTarget in(final Object targetObject) {
        try {
            if (targetObject instanceof Class<?>) {
                return in((Class<?>) targetObject);
            } else {
                return new RMethodWithTarget(methodName, targetObject);
            }
        } finally {
            StackTraces.store("in");
        }
    }

    public <ReturnType> RMethodWithReturnType<ReturnType> withReturnType(
            final Class<ReturnType> returnType) {
        return new RMethodWithReturnType<ReturnType>(methodName, returnType);
    }
}
