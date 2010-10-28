package org.jreflect;

public class RMethodWithReturnType<ReturnType> {
	private final String name;
	private final Class<ReturnType> returnType;

	public RMethodWithReturnType(String name, Class<ReturnType> returnType) {
		this.name = name;
		this.returnType = returnType;
	}

	public RMethodWithReturnTypeAndTarget<ReturnType> in(Object targetObject) {
		return new RMethodWithReturnTypeAndTarget<ReturnType>(name, returnType,
				targetObject);
	}
}
