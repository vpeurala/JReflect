package org.jreflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Arguments {
    /**
     * http://java.sun.com/docs/books/jls/third_edition/html/conversions.html
     * 
     * 5.1.2 Widening Primitive Conversion
     * 
     * The following 19 specific conversions on primitive types are called the
     * widening primitive conversions:
     * 
     * # byte to short, int, long, float, or double
     * # short to int, long, float, or double
     * # char to int, long, float, or double
     * # int to long, float, or double
     * # long to float or double # float to double
     */
    @SuppressWarnings("serial")
    private static final Map<Class<?>, List<Class<?>>> coercionRules = new HashMap<Class<?>, List<Class<?>>>() {
        {
            put(Byte.class, Arrays.<Class<?>> asList(Byte.TYPE, Short.TYPE,
                    Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE));
            put(Short.class, Arrays.<Class<?>> asList(Short.TYPE, Integer.TYPE,
                    Long.TYPE, Float.TYPE, Double.TYPE));
            put(Character.class, Arrays.<Class<?>> asList(Character.TYPE,
                    Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE));
            put(Integer.class, Arrays.<Class<?>> asList(Integer.TYPE,
                    Long.TYPE, Float.TYPE, Double.TYPE));
            put(Long.class, Arrays.<Class<?>> asList(Long.TYPE, Float.TYPE,
                    Double.TYPE));
            put(Float.class, Arrays.<Class<?>> asList(Float.TYPE, Double.TYPE));
            put(Double.class, Arrays.<Class<?>> asList(Double.TYPE));
            put(Boolean.class, Arrays.<Class<?>> asList(Boolean.TYPE));
        }
    };
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

    boolean matchesMethod(final Method m) {
        final List<Class<?>> methodParameterTypes = Arrays.asList(m
                .getParameterTypes());
        if (methodParameterTypes.size() != arguments.size()) {
            return false;
        }
        for (int i = 0; i < methodParameterTypes.size(); i++) {
            final Class<?> methodParameterType = methodParameterTypes.get(i);
            final Option<Class<?>> argumentClass = argumentClasses.get(i);
            if (argumentClass.isSome()
                    && !isExactOrCoercablePrimitiveVersionOf(
                            methodParameterType, argumentClass.getValue())
                            && !methodParameterType.isAssignableFrom(argumentClass
                                    .getValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean isExactOrCoercablePrimitiveVersionOf(
            final Class<?> possiblePrimitiveType,
            final Class<?> possibleWrapperType) {
        if (!possiblePrimitiveType.isPrimitive()) {
            return false;
        }
        final List<Class<?>> coercions = coercionRules.get(possibleWrapperType);
        return coercions.contains(possiblePrimitiveType);
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
