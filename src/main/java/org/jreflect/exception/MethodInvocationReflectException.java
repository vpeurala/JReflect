package org.jreflect.exception;

import java.lang.reflect.Method;

import org.jreflect.engine.TargetMember;

public class MethodInvocationReflectException extends ReflectException {
    public MethodInvocationReflectException(
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target, final Method closestMatch) {
        super(buildMessage(TargetType.METHOD, invocationType, failureType,
                target, closestMatch));
    }

    private static String buildMessage(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target, final Method closestMatch) {
        return new ReflectExceptionMessageBuilder(targetType, invocationType,
                failureType, target, closestMatch).buildMessage();
    }
}
