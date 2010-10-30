package org.jreflect.fields.instances;

import org.jreflect.Reflect;
import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.fixture.ClassWithStaticFields;
import org.junit.After;

public class StaticFieldOnInstanceTest extends
        AbstractFieldTestCase<ClassWithStaticFields> {
    @After
    public void tearDown() {
        Reflect.field(fieldName()).in(ClassWithStaticFields.class).setValue(0);
    }

    @Override
    protected String fieldName() {
        return "staticIntField";
    }

    @Override
    protected ClassWithStaticFields target() {
        return new ClassWithStaticFields();
    }
}
