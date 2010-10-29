package org.jreflect.methods;

import static org.jreflect.JReflect.field;
import static org.jreflect.JReflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public abstract class AbstractMethodTestCase<T> {
    @Test
    public void canInvokeMethodWithNoParametersAndNoReturnValue() {
        final String methodName = "methodWithNoParametersAndNoReturnValue";
        method(methodName).in(target()).invoke();
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithNoParametersAndReturnValue() {
        final String methodName = "methodWithNoParametersAndReturnValue";
        final int value = method(methodName).withReturnType(Integer.class)
                .in(target()).invoke();
        assertEquals(1, value);
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithParametersAndNoReturnValue() {
        final String methodName = "methodWithParametersAndNoReturnValue";
        method(methodName).in(target()).invoke("some string", 1, true);
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithParametersAndReturnValue() {
        final String methodName = "methodWithParametersAndReturnValue";
        final int value = method(methodName).withReturnType(Integer.class)
                .in(target()).invoke(4, 5.6f, null);
        assertEquals(2, value);
        assertMethodInvoked(methodName);
    }

    private void assertMethodInvoked(final String methodName) {
        assertTrue(field(methodName + "Called").ofType(Boolean.class)
                .in(target()).getValue());
    }

    protected abstract T target();
}
