package org.jreflect.fields.statics;

import org.jreflect.JReflect;
import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.fixture.ClassWithStaticFields;
import org.junit.After;

public class StaticFieldTest extends
        AbstractFieldTestCase<Class<ClassWithStaticFields>> {
    @After
    public void tearDown() {
        JReflect.field(fieldName()).in(ClassWithStaticFields.class).setValue(0);
    }

    @Override
    protected String fieldName() {
        return "staticIntField";
    }

    @Override
    protected Class<ClassWithStaticFields> target() {
        return ClassWithStaticFields.class;
    }
}
