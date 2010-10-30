package org.jreflect.methods;

import static org.jreflect.Reflect.field;
import static org.jreflect.Reflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jreflect.RFieldWithTargetAndType;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public abstract class AbstractMethodTestCase<T> {
    private String currentMethodName;

    @After
    public void tearDown() {
        resetMethodInvokedFlag(currentMethodName);
    }

    @Test
    public void canInvokeMethodWithNoParametersAndNoReturnValue() {
        currentMethodName = methodPrefix() + "WithNoParametersAndNoReturnValue";
        method(currentMethodName).in(target()).invoke();
        assertMethodInvokedFlag(currentMethodName);
    }

    @Test
    public void canInvokeMethodWithNoParametersAndReturnValue() {
        currentMethodName = methodPrefix() + "WithNoParametersAndReturnValue";
        final int value = method(currentMethodName)
                .withReturnType(Integer.class).in(target()).invoke();
        assertEquals(1, value);
        assertMethodInvokedFlag(currentMethodName);
    }

    @Test
    public void canInvokeMethodWithParametersAndNoReturnValue() {
        currentMethodName = methodPrefix() + "WithParametersAndNoReturnValue";
        method(currentMethodName).in(target()).invoke("some string", 1, true);
        assertMethodInvokedFlag(currentMethodName);
    }

    @Test
    public void canInvokeMethodWithParametersAndReturnValue() {
        currentMethodName = methodPrefix() + "WithParametersAndReturnValue";
        final int value = method(currentMethodName)
                .withReturnType(Integer.class).in(target())
                .invoke(4, 5.6f, null);
        assertEquals(2, value);
        assertMethodInvokedFlag(currentMethodName);
    }

    private void assertMethodInvokedFlag(final String methodName) {
        assertTrue(flagField(methodName).getValue());
    }

    private void resetMethodInvokedFlag(final String methodName) {
        flagField(methodName).setValue(false);
    }

    private RFieldWithTargetAndType<Boolean> flagField(final String methodName) {
        return field(methodName + "Called").ofType(Boolean.class).in(target());
    }

    protected abstract T target();

    protected abstract String methodPrefix();
}
