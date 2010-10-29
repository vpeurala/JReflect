package org.jreflect.fields.instances;

import org.jreflect.JReflect;
import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.statics.ClassWithStaticFields;
import org.junit.After;

public class StaticFieldsOnInstancesTest extends
        AbstractFieldTestCase<ClassWithStaticFields> {
    @After
    public void tearDown() {
        JReflect.field(fieldName()).in(ClassWithStaticFields.class).setValue(0);
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
