package org.jreflect.methods.inheritance;

import org.jreflect.methods.AbstractMethodTestCase;
import org.jreflect.methods.fixture.ClassWithInstanceMethods;
import org.junit.Before;

public class InstanceMethodOnSuperclassTest extends
        AbstractMethodTestCase<ClassWithInstanceMethods> {
    private ClassWithInstanceMethods classWithInstanceMethods;

    @Before
    public void setUp() {
        classWithInstanceMethods = new ClassWithInstanceMethods();
    }

    @Override
    protected ClassWithInstanceMethods target() {
        return classWithInstanceMethods;
    }

    @Override
    protected String methodPrefix() {
        return "superclassMethod";
    }
}
