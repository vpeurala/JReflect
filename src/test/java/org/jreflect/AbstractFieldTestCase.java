package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public abstract class AbstractFieldTestCase<T> {
    @Test
    public void canGetFieldValueAsUntypedObject() {
        final Object value;
        value = field(fieldName()).in(target()).getValue();
        assertEquals(0, value);
    }

    @Test
    public void canGetFieldValueAsTypedObject() {
        final int value = field(fieldName()).ofType(Integer.class).in(target())
                .getValue();
        assertEquals(0, value);
    }

    @Test
    public void canSetFieldValueAsUntypedObject() {
        field(fieldName()).in(target()).setValue(1);
        final Object valueAfterSet = field(fieldName()).in(target()).getValue();
        assertEquals(1, valueAfterSet);
    }

    @Test
    public void canSetFieldValueAsTypedObject() {
        field(fieldName()).ofType(Integer.class).in(target()).setValue(1);
        final int valueAfterSet = field(fieldName()).ofType(Integer.class)
                .in(target()).getValue();
        assertEquals(1, valueAfterSet);
    }

    protected abstract String fieldName();

    protected abstract T target();
}
