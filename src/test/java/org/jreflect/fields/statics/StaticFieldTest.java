package org.jreflect.fields.statics;

import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.statics.StaticFieldTest.ClassWithStaticFields;

public class StaticFieldTest extends
        AbstractFieldTestCase<Class<ClassWithStaticFields>> {
    @Override
    protected String fieldName() {
        return "staticIntField";
    }

    @Override
    protected Class<ClassWithStaticFields> target() {
        return ClassWithStaticFields.class;
    }

    @SuppressWarnings("unused")
    public static class ClassWithStaticFields {
        private static int staticIntField;
    }
}
