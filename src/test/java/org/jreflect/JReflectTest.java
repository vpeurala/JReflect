package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.jreflect.JReflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JReflectTest {
	Object targetObject = new TestClass();

	@Test
	public void canGetFieldValueAsUntypedObject() {
		assertEquals(0, field("intField").in(targetObject).getValue());
	}

	@Test
	public void canGetFieldValueAsTypedObject() {
		int value = field("intField").ofType(Integer.class).in(targetObject)
				.getValue();
		assertEquals(0, value);
	}

	@Test
	public void canSetFieldValueAsUntypedObject() {
		field("intField").in(targetObject).setValue(1);
		assertEquals(1, field("intField").in(targetObject).getValue());
	}

	@Test
	public void canSetFieldValueAsTypedObject() {
		field("intField").ofType(Integer.class).in(targetObject).setValue(1);
		assertEquals(1,
				(int) field("intField").ofType(Integer.class).in(targetObject)
						.getValue());
	}

	@Test
	public void canInvokeMethodWithNoParametersAndNoReturnValue() {
		final String methodName = "methodWithNoParametersAndNoReturnValue";
		method(methodName).in(targetObject).invoke();
		assertMethodInvoked(methodName);
	}

	@Test
	public void canInvokeMethodWithNoParametersAndIntReturnValue() {
		final String methodName = "methodWithNoParametersAndIntReturnValue";
		int value = method(methodName).withReturnType(Integer.class)
				.in(targetObject).invoke();
		assertEquals(1, value);
		assertMethodInvoked(methodName);
	}

	private void assertMethodInvoked(String methodName) {
		assertTrue(field(methodName + "Called").ofType(Boolean.class)
				.in(targetObject).getValue());
	}
}
