package org.jreflect.methods.fixture;

@SuppressWarnings("unused")
public class SuperclassWithInstanceMethods extends Hyperclass {
    private boolean superclassMethodWithNoParametersAndNoReturnValueCalled;
    private boolean superclassMethodWithNoParametersAndReturnValueCalled;
    private boolean superclassMethodWithParametersAndNoReturnValueCalled;
    private boolean superclassMethodWithParametersAndReturnValueCalled;

    private void superclassMethodWithNoParametersAndNoReturnValue() {
        superclassMethodWithNoParametersAndNoReturnValueCalled = true;
    }

    private int superclassMethodWithNoParametersAndReturnValue() {
        superclassMethodWithNoParametersAndReturnValueCalled = true;
        return 1;
    }

    private void superclassMethodWithParametersAndNoReturnValue(final String s,
            final int i, final boolean b) {
        superclassMethodWithParametersAndNoReturnValueCalled = true;
    }

    private Integer superclassMethodWithParametersAndReturnValue(final long l,
            final double d, final String a) {
        superclassMethodWithParametersAndReturnValueCalled = true;
        return 2;
    }
}