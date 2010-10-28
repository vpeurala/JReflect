package org.jreflect;

public class RMethod {
	private final String name;

	public RMethod(String name) {
		this.name = name;
	}

	public RMethodWithTarget in(Object targetObject) {
		return new RMethodWithTarget(name, targetObject);
	}

	public <ReturnType> RMethodWithReturnType<ReturnType> withReturnType(
			Class<ReturnType> returnType) {
		return new RMethodWithReturnType<ReturnType>(name, returnType);
	}
}
