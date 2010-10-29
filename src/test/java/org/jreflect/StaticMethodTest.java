package org.jreflect;

import static org.jreflect.JReflect.method;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StaticMethodTest {
    @Test
    public void canInvokeStaticMethodWithNoReturnValue() {
        method("staticMethodWithParametersAndNoReturnValue").in(
                ClassWithStaticMethods.class).invoke((byte) 1, (short) 2, '3',
                4, 5l, 6f, 7d, true, "foo");
    }

    @Test
    public void canInvokeStaticMethodWithReturnValue() {
        final String value = method("staticMethodWithParametersAndReturnValue")
                .withReturnType(String.class).in(ClassWithStaticMethods.class)
                .invoke((byte) 1, (short) 2, '3', 4, 5l, 6f, 7d, true, "foo");
        assertEquals("123456.07.0truefoo", value);
    }

    @SuppressWarnings("unused")
    public static class ClassWithStaticMethods {
        private static void staticMethodWithParametersAndNoReturnValue(
                final byte b, final short s, final char c, final int i,
                final long l, final float f, final double d,
                final boolean bool, final Object o) {
            System.out.println("" + b + s + c + i + l + f + d + bool + o);
        }

        private static Object staticMethodWithParametersAndReturnValue(
                final byte b, final short s, final char c, final int i,
                final long l, final float f, final double d,
                final boolean bool, final Object o) {
            return "" + b + s + c + i + l + f + d + bool + o;
        }
    }
}
