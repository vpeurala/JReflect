package org.jreflect.domain;

import org.jreflect.exception.StackTraces;

public class RMethodWithReturnType<ReturnType> {
    private final String methodName;
    private final Class<ReturnType> returnType;

    public RMethodWithReturnType(final String methodName,
            final Class<ReturnType> returnType) {
        this.methodName = methodName;
        this.returnType = returnType;
    }

    public RMethodWithReturnTypeAndTarget<ReturnType> in(
            final Class<?> targetClass) {
        try {
            return new RMethodWithReturnTypeAndTarget<ReturnType>(methodName,
                    targetClass, returnType);
        } finally {
            StackTraces.store("in");
        }
    }

    public RMethodWithReturnTypeAndTarget<ReturnType> in(
            final Object targetObject) {
        try {
            if (targetObject instanceof Class<?>) {
                return in((Class<?>) targetObject);
            } else {
                return new RMethodWithReturnTypeAndTarget<ReturnType>(
                        methodName, targetObject, returnType);
            }
        } finally {
            StackTraces.store("in");
        }
    }
}
