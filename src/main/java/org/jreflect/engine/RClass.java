package org.jreflect.engine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RClass {
    private static final Map<Class<?>, Class<?>> autoboxingRules = new HashMap<Class<?>, Class<?>>() {
        {
            put(Byte.class, Byte.TYPE);
            put(Short.class, Short.TYPE);
            put(Character.class, Character.TYPE);
            put(Integer.class, Integer.TYPE);
            put(Long.class, Long.TYPE);
            put(Float.class, Float.TYPE);
            put(Double.class, Double.TYPE);
            put(Boolean.class, Boolean.TYPE);
        }
    };
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
     * # long to float or double
     * # float to double
     */
    @SuppressWarnings("serial")
    private static final Map<Class<?>, List<Class<?>>> coercionRules = new HashMap<Class<?>, List<Class<?>>>() {
        {
            // # byte to short, int, long, float, or double
            put(Byte.TYPE, Arrays.<Class<?>> asList(Short.TYPE, Integer.TYPE,
                    Long.TYPE, Float.TYPE, Double.TYPE));
            // # short to int, long, float, or double
            put(Short.TYPE, Arrays.<Class<?>> asList(Integer.TYPE, Long.TYPE,
                    Float.TYPE, Double.TYPE));
            // # char to int, long, float, or double
            put(Character.TYPE, Arrays.<Class<?>> asList(Integer.TYPE,
                    Long.TYPE, Float.TYPE, Double.TYPE));
            // # int to long, float, or double
            put(Integer.TYPE, Arrays.<Class<?>> asList(Long.TYPE, Float.TYPE,
                    Double.TYPE));
            // # long to float or double
            put(Long.TYPE, Arrays.<Class<?>> asList(Float.TYPE, Double.TYPE));
            // # float to double
            put(Float.TYPE, Arrays.<Class<?>> asList(Double.TYPE));
        }
    };
    private final Class<?> klass;

    public RClass(final Class<?> klass) {
        this.klass = klass;
    }

    public boolean isAssignableFrom(final Class<?> other) {
        return klass.isAssignableFrom(other)
                || isAutoboxable(other, klass)
                || isCoercable(other, klass)
                || (klass.isPrimitive() && isCoercable(
                        autoboxingRules.get(other), klass));
    }

    public boolean isAssignableFrom(final RClass other) {
        return isAssignableFrom(other.klass);
    }

    public boolean isAssignableTo(final Class<?> other) {
        return other.isAssignableFrom(klass)
                || isAutoboxable(klass, other)
                || isCoercable(klass, other)
                || (other.isPrimitive() && isCoercable(
                        autoboxingRules.get(klass), other));
    }

    public boolean isAssignableTo(final RClass other) {
        return isAssignableTo(other.klass);
    }

    private boolean isAutoboxable(final Class<?> from, final Class<?> to) {
        return (autoboxingRules.containsKey(from) && autoboxingRules.get(from)
                .equals(to))
                || (autoboxingRules.containsKey(to) && autoboxingRules.get(to)
                        .equals(from));
    }

    private boolean isCoercable(final Class<?> from, final Class<?> to) {
        return coercionRules.containsKey(from)
                && coercionRules.get(from).contains(to);
    }
}
