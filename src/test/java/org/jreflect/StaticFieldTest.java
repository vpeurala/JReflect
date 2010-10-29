package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StaticFieldTest {
    @Test
    public void canGetStaticFieldValueAsUntypedObject() {
        final Object value = field("staticIntField").in(
                ClassWithStaticFields.class).getValue();
        assertEquals(0, value);
    }

    @Test
    public void canGetStaticFieldValueAsTypedObject() {
        final int value = field("staticIntField").ofType(Integer.TYPE)
                .in(ClassWithStaticFields.class).getValue();
        assertEquals(0, value);
    }

    @SuppressWarnings("unused")
    public static class ClassWithStaticFields {
        private static int staticIntField;
    }
}
