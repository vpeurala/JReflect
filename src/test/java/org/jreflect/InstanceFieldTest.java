package org.jreflect;

import org.jreflect.InstanceFieldTest.ClassWithInstanceFields;
import org.junit.Before;

public class InstanceFieldTest extends
        AbstractFieldTest<ClassWithInstanceFields> {
    private ClassWithInstanceFields classWithInstanceFields;

    @Before
    public void setUp() {
        classWithInstanceFields = new ClassWithInstanceFields();
    }

    @Override
    protected String fieldName() {
        return "intField";
    }

    @Override
    protected ClassWithInstanceFields target() {
        return classWithInstanceFields;
    }

    @SuppressWarnings("unused")
    public static class ClassWithInstanceFields {
        private int intField;
    }
}
