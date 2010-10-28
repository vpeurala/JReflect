package org.jreflect;

public class TestClass {
	private int intField;
	private boolean methodWithNoParametersAndNoReturnValueCalled;
	private boolean methodWithNoParametersAndIntReturnValueCalled;

	private void methodWithNoParametersAndNoReturnValue() {
		methodWithNoParametersAndNoReturnValueCalled = true;
	}

	private int methodWithNoParametersAndIntReturnValue() {
		methodWithNoParametersAndIntReturnValueCalled = true;
		return 1;
	}
}
