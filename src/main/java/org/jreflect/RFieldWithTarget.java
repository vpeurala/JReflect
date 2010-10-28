package org.jreflect;

import static org.jreflect.ReflectionHelper.getValueFromField;
import static org.jreflect.ReflectionHelper.setValueOnField;

public class RFieldWithTarget {
	private final String name;
	private final Object targetObject;

	public RFieldWithTarget(String name, Object targetObject) {
		this.name = name;
		this.targetObject = targetObject;
	}

	public Object getValue() {
		return getValueFromField(targetObject, name);
	}

	public void setValue(Object value) {
		setValueOnField(targetObject, name, value);
	}
}
