package org.jreflect.fields.instances;

import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.instances.InstanceFieldTest.ClassWithInstanceFields;
import org.junit.Before;

public class InstanceFieldTest extends
        AbstractFieldTestCase<ClassWithInstanceFields> {
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
