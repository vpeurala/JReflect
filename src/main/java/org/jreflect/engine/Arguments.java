package org.jreflect.engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Arguments {
    private final List<Object> arguments = new ArrayList<Object>();
    private final List<Option<Class<?>>> argumentClasses = new ArrayList<Option<Class<?>>>();

    Arguments(final Object... args) {
        for (final Object arg : args) {
            arguments.add(arg);
            if (arg == null) {
                argumentClasses.add(new Option.None<Class<?>>());
            } else {
                argumentClasses.add(new Option.Some<Class<?>>(arg.getClass()));
            }
        }
    }

    Object[] get() {
        return arguments.toArray(new Object[arguments.size()]);
    }

    boolean matchesMethod(final Method candidateMethod) {
        final List<Class<?>> methodParameterTypes = Arrays
                .asList(candidateMethod.getParameterTypes());
        return matchesParameters(methodParameterTypes);
    }

    boolean matchesConstructor(final Constructor<?> candidateConstructor) {
        final List<Class<?>> constructorParameterTypes = Arrays
                .asList(candidateConstructor.getParameterTypes());
        return matchesParameters(constructorParameterTypes);
    }

    boolean matchesParameters(final List<Class<?>> parameterTypes) {
        if (parameterTypes.size() != arguments.size()) {
            return false;
        }
        for (int i = 0; i < parameterTypes.size(); i++) {
            final Class<?> methodParameterType = parameterTypes.get(i);
            final Option<Class<?>> argumentClass = argumentClasses.get(i);
            if (argumentClass.isSome()
                    && !(new RClass(methodParameterType)
                            .isAssignableFrom(argumentClass.getValue()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < argumentClasses.size(); i++) {
            if (i != 0) {
                buffer.append(", ");
            }
            final Option<Class<?>> argumentClassOption = argumentClasses.get(i);
            buffer.append(argumentClassOption.isSome() ? argumentClassOption
                    .getValue().getSimpleName() : "UNKNOWN");
        }
        return buffer.toString();
    }
}
