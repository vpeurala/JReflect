package org.jreflect.engine;

import static org.jreflect.exception.FailureType.NOT_FOUND_BY_MATCHING_PARAMETERS;
import static org.jreflect.exception.FailureType.NOT_FOUND_BY_MATCHING_RETURN_TYPE;
import static org.jreflect.exception.FailureType.NOT_FOUND_BY_NAME;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.jreflect.exception.InvocationTargetReflectException;
import org.jreflect.exception.MethodInvocationReflectException;
import org.jreflect.exception.ReflectException;
import org.jreflect.exception.TargetType;

public abstract class TargetMethod<SelfType> {
    private final TargetMember target;
    private final String methodName;
    private final Arguments arguments;
    private Method targetMethod;
    protected Object returnValue;

    TargetMethod(final TargetMember target, final String methodName,
            final Object... arguments) {
        this.target = target;
        this.methodName = methodName;
        this.arguments = new Arguments(arguments);
    }

    @SuppressWarnings("unchecked")
    public SelfType invoke() {
        targetMethod = findMethod();
        if (!targetMethod.isAccessible()) {
            targetMethod.setAccessible(true);
        }
        try {
            returnValue = targetMethod.invoke(target.targetObject(),
                    arguments.get());
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (final InvocationTargetException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Error) {
                throw (Error) cause;
            } else {
                throw new InvocationTargetReflectException(e.getCause());
            }
        }
        return (SelfType) this;
    }

    protected abstract boolean candidateMethodMatches(Method candidateMethod);

    private final Method findMethod() {
        final List<Method> methods = Methods
                .allMethodsOfClassAndSuperclasses(target.targetClass());
        boolean foundByName = false;
        boolean foundByParams = false;
        Method closestMatch = null;
        for (final Method method : methods) {
            if (method.getName().equals(methodName)) {
                foundByName = true;
                closestMatch = method;
            }
            if (method.getName().equals(methodName)
                    && arguments.matchesMethod(method)) {
                foundByParams = true;
                closestMatch = method;
                if (candidateMethodMatches(method)) {
                    return method;
                }
            }
        }
        if (!foundByName) {
            throw new ReflectException(TargetType.METHOD,
                    target.invocationType(), NOT_FOUND_BY_NAME, target);
        } else if (!foundByParams) {
            throw new MethodInvocationReflectException(target.invocationType(),
                    NOT_FOUND_BY_MATCHING_PARAMETERS, target, closestMatch);
        } else {
            throw new ReflectException(TargetType.METHOD,
                    target.invocationType(), NOT_FOUND_BY_MATCHING_RETURN_TYPE,
                    target);
        }
    }
}
