package org.jreflect.methods.fixture;

@SuppressWarnings("unused")
public class ClassWithStaticMethods extends SuperclassWithStaticMethods {
    private static boolean methodWithNoParametersAndNoReturnValueCalled;
    private static boolean methodWithNoParametersAndReturnValueCalled;
    private static boolean methodWithParametersAndNoReturnValueCalled;
    private static boolean methodWithParametersAndReturnValueCalled;

    private static void methodWithNoParametersAndNoReturnValue() {
        methodWithNoParametersAndNoReturnValueCalled = true;
    }

    private static int methodWithNoParametersAndReturnValue() {
        methodWithNoParametersAndReturnValueCalled = true;
        return 1;
    }

    private static void methodWithParametersAndNoReturnValue(final String s,
            final int i, final boolean b) {
        methodWithParametersAndNoReturnValueCalled = true;
    }

    private static Integer methodWithParametersAndReturnValue(final long l,
            final double d, final String a) {
        methodWithParametersAndReturnValueCalled = true;
        return 2;
    }
}