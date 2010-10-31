package org.jreflect.domain;

import static org.jreflect.engine.Target.forClass;
import static org.jreflect.engine.Target.forObject;

import org.jreflect.engine.Target;

public class RMethodWithReturnTypeAndTarget<ReturnType> {
    private final String methodName;
    private final Target target;
    private final Class<ReturnType> returnType;

    public RMethodWithReturnTypeAndTarget(final String methodName,
            final Class<?> targetClass, final Class<ReturnType> returnType) {
        this.methodName = methodName;
        this.target = forClass(targetClass);
        this.returnType = returnType;
    }

    public RMethodWithReturnTypeAndTarget(final String methodName,
            final Object targetObject, final Class<ReturnType> returnType) {
        this.methodName = methodName;
        this.target = forObject(targetObject);
        this.returnType = returnType;
    }

    public ReturnType invoke(final Object... args) {
        return returnType.cast(target.forMethod(methodName, returnType, args)
                .invoke());
    }
}
