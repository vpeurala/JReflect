package org.jreflect.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.jreflect.domain.ReflectException;
import org.jreflect.domain.ReflectException.FailureType;
import org.jreflect.domain.ReflectException.InvocationType;
import org.jreflect.domain.ReflectException.TargetType;

public class TargetMethod<ReturnType> {
    private final Target target;
    private final String methodName;
    private final Class<ReturnType> returnType;
    private final Object[] arguments;
    private final Method targetMethod;

    TargetMethod(final Target target, final String methodName,
            final Class<ReturnType> returnType, final Object... arguments) {
        this.target = target;
        this.methodName = methodName;
        this.returnType = returnType;
        this.arguments = arguments;
        targetMethod = findMethod();
        targetMethod.setAccessible(true);
    }

    public ReturnType invoke() {
        try {
            return returnType.cast(targetMethod.invoke(target.targetObject(),
                    arguments));
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
                throw new ReflectException(e.getCause());
            }
        }
    }

    private Method findMethod() {
        final List<Method> methods = Methods
                .allMethodsOfClassAndSuperclasses(target.targetClass());
        final Arguments arguments2 = new Arguments(arguments);
        for (final Method method : methods) {
            if (method.getName().equals(methodName)
                    && arguments2.matchesMethod(method)) {
                // FIXME VP                    && method.getReturnType().equals(returnType)) {
                return method;
            }
        }
        throw reflectException();
    }

    private ReflectException reflectException() {
        // FIXME VP Wrong parameters
        return new ReflectException(TargetType.METHOD, InvocationType.INSTANCE,
                FailureType.NOT_FOUND_BY_NAME, target);
    }
}
