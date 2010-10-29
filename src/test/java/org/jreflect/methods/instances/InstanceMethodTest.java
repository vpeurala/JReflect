package org.jreflect.methods.instances;

import org.jreflect.methods.AbstractMethodTestCase;
import org.jreflect.methods.instances.InstanceMethodTest.ClassWithInstanceMethods;
import org.junit.Before;

public class InstanceMethodTest extends
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

    @SuppressWarnings("unused")
    public static class ClassWithInstanceMethods {
        private boolean methodWithNoParametersAndNoReturnValueCalled;
        private boolean methodWithNoParametersAndReturnValueCalled;
        private boolean methodWithParametersAndNoReturnValueCalled;
        private boolean methodWithParametersAndReturnValueCalled;

        private void methodWithNoParametersAndNoReturnValue() {
            methodWithNoParametersAndNoReturnValueCalled = true;
        }

        private int methodWithNoParametersAndReturnValue() {
            methodWithNoParametersAndReturnValueCalled = true;
            return 1;
        }

        private void methodWithParametersAndNoReturnValue(final String s,
                final int i, final boolean b) {
            methodWithParametersAndNoReturnValueCalled = true;
        }

        private Integer methodWithParametersAndReturnValue(final long l,
                final double d, final String a) {
            methodWithParametersAndReturnValueCalled = true;
            return 2;
        }
    }
}
