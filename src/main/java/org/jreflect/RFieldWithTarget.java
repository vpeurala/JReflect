package org.jreflect;

import static org.jreflect.ReflectionHelper.getValueFromField;

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
}
