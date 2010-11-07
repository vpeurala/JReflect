package org.jreflect.exception;

import org.jreflect.engine.TargetMember;

public class ReflectException extends RuntimeException {
    public ReflectException(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target) {
        super(buildMessage(targetType, invocationType, failureType, target));
    }

    protected ReflectException(final String message) {
        super(message);
    }

    protected ReflectException(final Throwable cause) {
        super("\nInvocation raised a checked exception:\n  "
                + cause.getClass().getName() + " {\n" + "    \""
                + cause.getMessage() + "\"\n"
                + formatStackTrace(cause.getStackTrace()) + "  }.\n", cause);
    }

    private static String formatStackTrace(final StackTraceElement[] stackTrace) {
        final StringBuilder buffer = new StringBuilder();
        final StackTraceElement frame = stackTrace[0];
        buffer.append("      at " + frame.toString());
        buffer.append('\n');
        return buffer.toString();
    }

    private static String buildMessage(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target) {
        return new ReflectExceptionMessageBuilder(targetType, invocationType,
                failureType, target, null).buildMessage();
    }
}
