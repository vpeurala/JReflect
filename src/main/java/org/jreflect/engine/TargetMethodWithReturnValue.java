package org.jreflect.engine;

import java.lang.reflect.Method;

public class TargetMethodWithReturnValue<ReturnType> extends
        TargetMethod<TargetMethodWithReturnValue<ReturnType>> {
    private final Class<ReturnType> returnType;

    TargetMethodWithReturnValue(final TargetMember target,
            final String methodName, final Class<ReturnType> returnType,
            final Object... arguments) {
        super(target, methodName, arguments);
        this.returnType = returnType;
    }

    public ReturnType returnValue() {
        return returnType.cast(returnValue);
    }

    @Override
    protected boolean candidateMethodMatches(final Method candidateMethod) {
        return new RClass(returnType).isAssignableFrom(candidateMethod
                .getReturnType());
    }
}
