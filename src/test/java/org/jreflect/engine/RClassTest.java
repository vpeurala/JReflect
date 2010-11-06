package org.jreflect.engine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RClassTest {
    @Test
    public void anyClassIsAssignableFromItself() {
        assertTrue(new RClass(Object.class).isAssignableFrom(Object.class));
        assertTrue(new RClass(Object.class).isAssignableFrom(new RClass(
                Object.class)));
    }

    @Test
    public void anyClassIsAssignableToItself() {
        assertTrue(new RClass(Object.class).isAssignableTo(Object.class));
        assertTrue(new RClass(Object.class).isAssignableTo(new RClass(
                Object.class)));
    }

    @Test
    public void subclassIsNotAssignableFromSuperclass() {
        assertFalse(new RClass(String.class).isAssignableFrom(Object.class));
        assertFalse(new RClass(String.class).isAssignableFrom(new RClass(
                Object.class)));
    }

    @Test
    public void subclassIsAssignableToSuperclass() {
        assertTrue(new RClass(String.class).isAssignableTo(Object.class));
        assertTrue(new RClass(String.class).isAssignableTo(new RClass(
                Object.class)));
    }

    @Test
    public void autoboxingWorksInBothDirections() {
        assertAutoboxingInEveryDirection(Byte.TYPE, Byte.class);
        assertAutoboxingInEveryDirection(Short.TYPE, Short.class);
        assertAutoboxingInEveryDirection(Character.TYPE, Character.class);
        assertAutoboxingInEveryDirection(Integer.TYPE, Integer.class);
        assertAutoboxingInEveryDirection(Long.TYPE, Long.class);
        assertAutoboxingInEveryDirection(Float.TYPE, Float.class);
        assertAutoboxingInEveryDirection(Double.TYPE, Double.class);
        assertAutoboxingInEveryDirection(Boolean.TYPE, Boolean.class);
    }

    private void assertAutoboxingInEveryDirection(final Class<?> primitive,
            final Class<?> wrapper) {
        assertTrue(new RClass(primitive).isAssignableFrom(wrapper));
        assertTrue(new RClass(primitive).isAssignableTo(wrapper));
        assertTrue(new RClass(wrapper).isAssignableFrom(primitive));
        assertTrue(new RClass(wrapper).isAssignableTo(primitive));
        assertTrue(new RClass(primitive).isAssignableFrom(new RClass(wrapper)));
        assertTrue(new RClass(primitive).isAssignableTo(new RClass(wrapper)));
        assertTrue(new RClass(wrapper).isAssignableFrom(new RClass(primitive)));
        assertTrue(new RClass(wrapper).isAssignableTo(new RClass(primitive)));
    }

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
    @Test
    public void coercionWorksInOneDirectionOnly() {
        // # byte to short, int, long, float, or double
        assertCoercable(Byte.TYPE, Short.TYPE);
        assertCoercable(Byte.TYPE, Integer.TYPE);
        assertCoercable(Byte.TYPE, Long.TYPE);
        assertCoercable(Byte.TYPE, Float.TYPE);
        assertCoercable(Byte.TYPE, Double.TYPE);
        // # short to int, long, float, or double
        assertCoercable(Short.TYPE, Integer.TYPE);
        assertCoercable(Short.TYPE, Long.TYPE);
        assertCoercable(Short.TYPE, Float.TYPE);
        assertCoercable(Short.TYPE, Double.TYPE);
        // # char to int, long, float, or double
        assertCoercable(Character.TYPE, Integer.TYPE);
        assertCoercable(Character.TYPE, Long.TYPE);
        assertCoercable(Character.TYPE, Float.TYPE);
        assertCoercable(Character.TYPE, Double.TYPE);
        // # int to long, float, or double
        assertCoercable(Integer.TYPE, Long.TYPE);
        assertCoercable(Integer.TYPE, Float.TYPE);
        assertCoercable(Integer.TYPE, Double.TYPE);
        // # long to float or double
        assertCoercable(Long.TYPE, Float.TYPE);
        assertCoercable(Long.TYPE, Double.TYPE);
        // # float to double
        assertCoercable(Float.TYPE, Double.TYPE);
    }

    @Test
    public void coercionAlsoWorkFromWrapperToPrimitiveBut() {
        // # byte to short, int, long, float, or double
        assertCoercable(Byte.class, Short.TYPE);
        assertCoercable(Byte.class, Integer.TYPE);
        assertCoercable(Byte.class, Long.TYPE);
        assertCoercable(Byte.class, Float.TYPE);
        assertCoercable(Byte.class, Double.TYPE);
        // # short to int, long, float, or double
        assertCoercable(Short.class, Integer.TYPE);
        assertCoercable(Short.class, Long.TYPE);
        assertCoercable(Short.class, Float.TYPE);
        assertCoercable(Short.class, Double.TYPE);
        // # char to int, long, float, or double
        assertCoercable(Character.class, Integer.TYPE);
        assertCoercable(Character.class, Long.TYPE);
        assertCoercable(Character.class, Float.TYPE);
        assertCoercable(Character.class, Double.TYPE);
        // # int to long, float, or double
        assertCoercable(Integer.class, Long.TYPE);
        assertCoercable(Integer.class, Float.TYPE);
        assertCoercable(Integer.class, Double.TYPE);
        // # long to float or double
        assertCoercable(Long.class, Float.TYPE);
        assertCoercable(Long.class, Double.TYPE);
        // # float to double
        assertCoercable(Float.class, Double.TYPE);
    }

    private void assertCoercable(final Class<?> from, final Class<?> to) {
        assertTrue(new RClass(from).isAssignableTo(to));
        assertFalse(new RClass(from).isAssignableFrom(to));
        assertTrue(new RClass(from).isAssignableTo(new RClass(to)));
        assertFalse(new RClass(from).isAssignableFrom(new RClass(to)));
    }
}
