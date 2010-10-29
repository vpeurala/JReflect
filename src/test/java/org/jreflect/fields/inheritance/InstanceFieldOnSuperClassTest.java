package org.jreflect.fields.inheritance;

import org.jreflect.fields.AbstractFieldTestCase;
import org.jreflect.fields.fixture.ClassWithInstanceFields;
import org.junit.Before;

public class InstanceFieldOnSuperClassTest extends
        AbstractFieldTestCase<ClassWithInstanceFields> {
    private ClassWithInstanceFields classWithInstanceFields;

    @Before
    public void setUp() {
        classWithInstanceFields = new ClassWithInstanceFields();
    }

    @Override
    protected String fieldName() {
        return "instanceFieldOnSuperclass";
    }

    @Override
    protected ClassWithInstanceFields target() {
        return classWithInstanceFields;
    }
}
