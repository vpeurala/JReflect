package org.jreflect.domain;

import org.jreflect.engine.TargetMember;

public class RMethodWithTarget {
    private final String methodName;
    private final TargetMember target;

    public RMethodWithTarget(final String methodName, final Class<?> targetClass) {
        this(methodName, TargetMember.forClass(methodName, targetClass));
    }

    public RMethodWithTarget(final String methodName, final Object targetObject) {
        this(methodName, TargetMember.forObject(methodName, targetObject));
    }

    private RMethodWithTarget(final String methodName, final TargetMember target) {
        this.methodName = methodName;
        this.target = target;
    }

    public void invoke(final Object... args) {
        target.forMethodDiscardingReturnValue(methodName, args).invoke();
    }
}
