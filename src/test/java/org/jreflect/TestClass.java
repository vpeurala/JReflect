package org.jreflect;

public class TestClass {
	private int intField;
	private boolean methodWithNoParametersAndNoReturnValueCalled;

	private void methodWithNoParametersAndNoReturnValue() {
		methodWithNoParametersAndNoReturnValueCalled = true;
	}
}
