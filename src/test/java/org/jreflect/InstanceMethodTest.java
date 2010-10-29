package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.jreflect.JReflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InstanceMethodTest {
    private final ClassWithInstanceMethods targetObject = new ClassWithInstanceMethods();

    @Test
    public void canInvokeMethodWithNoParametersAndNoReturnValue() {
        final String methodName = "methodWithNoParametersAndNoReturnValue";
        method(methodName).in(targetObject).invoke();
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithNoParametersAndIntReturnValue() {
        final String methodName = "methodWithNoParametersAndIntReturnValue";
        final int value = method(methodName).withReturnType(Integer.class)
                .in(targetObject).invoke();
        assertEquals(1, value);
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithParametersAndNoReturnValue() {
        final String methodName = "methodWithParametersAndNoReturnValue";
        method(methodName).in(targetObject).invoke("some string", 1, true);
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithParametersAndReturnValue() {
        final String methodName = "methodWithParametersAndReturnValue";
        final int value = method(methodName).withReturnType(Integer.class)
                .in(targetObject).invoke(4, 5.6f, null);
        assertEquals(2, value);
        assertMethodInvoked(methodName);
    }

    @SuppressWarnings("unused")
    public static class ClassWithInstanceMethods {
        private boolean methodWithNoParametersAndNoReturnValueCalled;
        private boolean methodWithNoParametersAndIntReturnValueCalled;
        private boolean methodWithParametersAndNoReturnValueCalled;
        private boolean methodWithParametersAndReturnValueCalled;

        private void methodWithNoParametersAndNoReturnValue() {
            methodWithNoParametersAndNoReturnValueCalled = true;
        }

        private int methodWithNoParametersAndIntReturnValue() {
            methodWithNoParametersAndIntReturnValueCalled = true;
            return 1;
        }

        private void methodWithParametersAndNoReturnValue(final String s,
                final int i, final boolean b) {
            methodWithParametersAndNoReturnValueCalled = true;
        }

        private Integer methodWithParametersAndReturnValue(final long l,
                final double d, final String a) {
            methodWithParametersAndReturnValueCalled = true;
            return 2;
        }
    }

    private void assertMethodInvoked(final String methodName) {
        assertTrue(field(methodName + "Called").ofType(Boolean.class)
                .in(targetObject).getValue());
    }
}
