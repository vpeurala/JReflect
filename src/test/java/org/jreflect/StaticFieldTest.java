package org.jreflect;

import org.jreflect.StaticFieldTest.ClassWithStaticFields;

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
