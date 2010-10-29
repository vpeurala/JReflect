package org.jreflect.methods.fixture;

@SuppressWarnings("unused")
public class SuperclassWithStaticMethods {
    private static boolean superclassMethodWithNoParametersAndNoReturnValueCalled;
    private static boolean superclassMethodWithNoParametersAndReturnValueCalled;
    private static boolean superclassMethodWithParametersAndNoReturnValueCalled;
    private static boolean superclassMethodWithParametersAndReturnValueCalled;

    private static void superclassMethodWithNoParametersAndNoReturnValue() {
        superclassMethodWithNoParametersAndNoReturnValueCalled = true;
    }

    private static int superclassMethodWithNoParametersAndReturnValue() {
        superclassMethodWithNoParametersAndReturnValueCalled = true;
        return 1;
    }

    private static void superclassMethodWithParametersAndNoReturnValue(
            final String s, final int i, final boolean b) {
        superclassMethodWithParametersAndNoReturnValueCalled = true;
    }

    private static Integer superclassMethodWithParametersAndReturnValue(
            final long l, final double d, final String a) {
        superclassMethodWithParametersAndReturnValueCalled = true;
        return 2;
    }
}
