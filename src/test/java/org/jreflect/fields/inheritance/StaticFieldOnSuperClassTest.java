package org.jreflect.fields.inheritance;

import org.jreflect.Reflect;
import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.fixture.ClassWithStaticFields;
import org.junit.After;

public class StaticFieldOnSuperClassTest extends
        AbstractFieldTestCase<Class<ClassWithStaticFields>> {
    @After
    public void setUp() {
        Reflect.field(fieldName()).in(ClassWithStaticFields.class).setValue(0);
    }

    @Override
    protected String fieldName() {
        return "staticFieldOnSuperclass";
    }

    @Override
    protected Class<ClassWithStaticFields> target() {
        return ClassWithStaticFields.class;
    }
}
