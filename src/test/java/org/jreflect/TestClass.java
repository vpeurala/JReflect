package org.jreflect;

@SuppressWarnings("unused")
public class TestClass {
    private int intField;
    private static int staticIntField;
    private boolean methodWithNoParametersAndNoReturnValueCalled;
    private boolean methodWithNoParametersAndIntReturnValueCalled;
    private boolean methodWithParametersAndNoReturnValueCalled;
    private boolean methodWithParametersAndReturnValueCalled;

    private static void staticMethodWithParametersAndNoReturnValue(
            final byte b, final short s, final char c, final int i,
            final long l, final float f, final double d, final boolean bool,
            final Object o) {
        System.out.println("" + b + s + c + i + l + f + d + bool + o);
    }

    private static Object staticMethodWithParametersAndReturnValue(
            final byte b, final short s, final char c, final int i,
            final long l, final float f, final double d, final boolean bool,
            final Object o) {
        return "" + b + s + c + i + l + f + d + bool + o;
    }

    private void methodWithNoParametersAndNoReturnValue() {
        methodWithNoParametersAndNoReturnValueCalled = true;
    }

    private int methodWithNoParametersAndIntReturnValue() {
        methodWithNoParametersAndIntReturnValueCalled = true;
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
