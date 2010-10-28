package org.jreflect;

import static org.jreflect.ReflectionHelper.invokeValueReturningMethod;

public class RMethodWithReturnTypeAndTarget<ReturnType> {
	private final String name;
	private final Class<ReturnType> returnType;
	private final Object targetObject;
	private final Class<?> targetClass;

	public RMethodWithReturnTypeAndTarget(String name,
			Class<ReturnType> returnType, Class<?> targetClass) {
		this(name, returnType, null, targetClass);
	}

	public RMethodWithReturnTypeAndTarget(String name,
			Class<ReturnType> returnType, Object targetObject) {
		this(name, returnType, targetObject, null);
	}

	private RMethodWithReturnTypeAndTarget(String name,
			Class<ReturnType> returnType, Object targetObject,
			Class<?> targetClass) {
		this.name = name;
		this.returnType = returnType;
		this.targetObject = targetObject;
		this.targetClass = targetClass;
	}

	public ReturnType invoke(Object... args) {
		if (targetObject != null) {
			return invokeValueReturningMethod(targetObject, name, returnType,
					args);
		} else {
			return invokeValueReturningMethod(targetClass, name, returnType,
					args);
		}
	}
}
