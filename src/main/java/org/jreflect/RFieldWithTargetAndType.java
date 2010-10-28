package org.jreflect;

import static org.jreflect.ReflectionHelper.getValueFromField;

public class RFieldWithTargetAndType<T> {
	private final String name;
	private final Object targetObject;

	public RFieldWithTargetAndType(String name, Object targetObject) {
		this.name = name;
		this.targetObject = targetObject;
	}

	public T getValue() {
		return getValueFromField(targetObject, name);
	}
}
