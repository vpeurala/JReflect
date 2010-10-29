package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstanceFieldTest {
    private final ClassWithInstanceFields targetObject = new ClassWithInstanceFields();

    @Test
    public void canGetFieldValueAsUntypedObject() {
        assertEquals(0, field("intField").in(targetObject).getValue());
    }

    @Test
    public void canGetFieldValueAsTypedObject() {
        final int value = field("intField").ofType(Integer.class)
                .in(targetObject).getValue();
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
        final int valueAfterSetting = field("intField").ofType(Integer.class)
                .in(targetObject).getValue();
        assertEquals(1, valueAfterSetting);
    }

    @SuppressWarnings("unused")
    public static class ClassWithInstanceFields {
        private int intField;
    }
}
