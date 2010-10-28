package org.jreflect;

public class RMethod {
	private final String name;

	public RMethod(String name) {
		this.name = name;
	}

	public RMethodWithTarget in(Object targetObject) {
		return new RMethodWithTarget(name, targetObject);
	}
}
