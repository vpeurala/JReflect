package org.jreflect.engine;

import java.lang.reflect.Method;

public class TargetMethodWithoutReturnValue extends
        TargetMethod<TargetMethodWithoutReturnValue> {
    TargetMethodWithoutReturnValue(final TargetMember target,
            final String methodName, final Object... arguments) {
        super(target, methodName, arguments);
    }

    @Override
    protected boolean candidateMethodMatches(final Method candidateMethod) {
        return true;
    }
}
