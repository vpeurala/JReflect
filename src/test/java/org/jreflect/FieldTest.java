package org.jreflect;

import static org.jreflect.JReflect.field;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FieldTest {
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
}
