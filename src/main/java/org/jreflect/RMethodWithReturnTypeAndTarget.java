package org.jreflect;

public class RMethodWithReturnTypeAndTarget<ReturnType> {
	private final String name;
	private final Class<ReturnType> returnType;
	private final Object targetObject;

	public RMethodWithReturnTypeAndTarget(String name,
			Class<ReturnType> returnType, Object targetObject) {
		this.name = name;
		this.returnType = returnType;
		this.targetObject = targetObject;
	}

	public ReturnType invoke(Object... args) {
		return ReflectionHelper.invokeValueReturningMethod(targetObject, name,
				returnType, args);
	}
}
