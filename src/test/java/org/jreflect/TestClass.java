package org.jreflect;

@SuppressWarnings("unused")
public class TestClass {
	private int intField;
	private boolean methodWithNoParametersAndNoReturnValueCalled;
	private boolean methodWithNoParametersAndIntReturnValueCalled;
	private boolean methodWithParametersAndNoReturnValueCalled;
	private boolean methodWithParametersAndReturnValueCalled;

	private void methodWithNoParametersAndNoReturnValue() {
		methodWithNoParametersAndNoReturnValueCalled = true;
	}

	private int methodWithNoParametersAndIntReturnValue() {
		methodWithNoParametersAndIntReturnValueCalled = true;
		return 1;
	}

	private void methodWithParametersAndNoReturnValue(String s, int i, boolean b) {
		methodWithParametersAndNoReturnValueCalled = true;
	}

	private Integer methodWithParametersAndReturnValue(long l, double d,
			String a) {
		methodWithParametersAndReturnValueCalled = true;
		return 2;
	}
}
