package org.jreflect.domain;

import org.jreflect.engine.TargetMember;

public class RMethodWithReturnTypeAndTarget<ReturnType> {
    private final String methodName;
    private final TargetMember target;
    private final Class<ReturnType> returnType;

    public RMethodWithReturnTypeAndTarget(final String methodName,
            final Class<?> targetClass, final Class<ReturnType> returnType) {
        this(methodName, TargetMember.forClass(methodName, targetClass),
                returnType);
    }

    public RMethodWithReturnTypeAndTarget(final String methodName,
            final Object targetObject, final Class<ReturnType> returnType) {
        this(methodName, TargetMember.forObject(methodName, targetObject),
                returnType);
    }

    private RMethodWithReturnTypeAndTarget(final String methodName,
            final TargetMember target, final Class<ReturnType> returnType) {
        this.methodName = methodName;
        this.target = target;
        this.returnType = returnType;
    }

    public ReturnType invoke(final Object... args) {
        return returnType.cast(target
                .forMethodKeepingReturnValue(methodName, returnType, args)
                .invoke().returnValue());
    }
}
