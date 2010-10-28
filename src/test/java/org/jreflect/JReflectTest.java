package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.jreflect.JReflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JReflectTest {
    Object targetObject = new TestClass();

    @Test
    public void canGetFieldValueAsUntypedObject() {
        assertEquals(0, field("intField").in(targetObject).getValue());
    }

    @Test
    public void canGetFieldValueAsTypedObject() {
        int value = field("intField").ofType(Integer.class).in(targetObject)
                .getValue();
        assertEquals(0, value);
    }

    @Test
    public void canSetFieldValueAsUntypedObject() {
        field("intField").in(targetObject).setValue(1);
        assertEquals(1, field("intField").in(targetObject).getValue());
    }

    @Test
    public void canSetFieldValueAsTypedObject() {
        field("intField").ofType(Integer.class).in(targetObject).setValue(1);
        int valueAfterSetting = field("intField").ofType(Integer.class)
                .in(targetObject).getValue();
        assertEquals(1, valueAfterSetting);
    }

    @Test
    public void canInvokeMethodWithNoParametersAndNoReturnValue() {
        final String methodName = "methodWithNoParametersAndNoReturnValue";
        method(methodName).in(targetObject).invoke();
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeMethodWithNoParametersAndIntReturnValue() {
        final String methodName = "methodWithNoParametersAndIntReturnValue";
        int value = method(methodName).withReturnType(Integer.class)
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
        int value = method(methodName).withReturnType(Integer.class)
                .in(targetObject).invoke(4, 5.6f, null);
        assertEquals(2, value);
        assertMethodInvoked(methodName);
    }

    @Test
    public void canInvokeStaticMethodWithNoReturnValue() {
        method("staticMethodWithParametersAndNoReturnValue")
                .in(TestClass.class).invoke((byte) 1, (short) 2, '3', 4, 5l,
                        6f, 7d, true, "foo");
    }

    @Test
    public void canInvokeStaticMethodWithReturnValue() {
        String value = method("staticMethodWithParametersAndReturnValue")
                .withReturnType(String.class).in(TestClass.class)
                .invoke((byte) 1, (short) 2, '3', 4, 5l, 6f, 7d, true, "foo");
        assertEquals("123456.07.0truefoo", value);
    }

    private void assertMethodInvoked(String methodName) {
        assertTrue(field(methodName + "Called").ofType(Boolean.class)
                .in(targetObject).getValue());
    }
}
