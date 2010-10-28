package org.jreflect;

public class RField {
	private final String name;

	public RField(String name) {
		this.name = name;
	}

	public RFieldWithTarget in(Object targetObject) {
		return new RFieldWithTarget(name, targetObject);
	}

	public <T> RFieldWithType<T> ofType(Class<T> type) {
		return new RFieldWithType<T>(name);
	}
}
