package org.jreflect;

public class RFieldWithTargetAndType<T> {
	private final String name;
	private final Object targetObject;

	public RFieldWithTargetAndType(String name, Object targetObject) {
		this.name = name;
		this.targetObject = targetObject;
	}

	public T getValue() {
		return ReflectionHelper.<T> getValueFromField(targetObject, name);
	}

	public void setValue(T value) {
		ReflectionHelper.setValueOnField(targetObject, name, value);
	}
}
