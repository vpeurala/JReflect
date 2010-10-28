package org.jreflect;

import static org.jreflect.ReflectionHelper.invokeVoidMethod;

public class RMethodWithTarget {
	private final String name;
	private final Object targetObject;
	private final Class<?> targetClass;

	public RMethodWithTarget(String name, Class<?> targetClass) {
		this(name, null, targetClass);
	}

	public RMethodWithTarget(String name, Object targetObject) {
		this(name, targetObject, null);
	}

	private RMethodWithTarget(String name, Object targetObject,
			Class<?> targetClass) {
		this.name = name;
		this.targetObject = targetObject;
		this.targetClass = targetClass;
	}

	public void invoke(Object... args) {
		if (targetObject != null) {
			invokeVoidMethod(targetObject, name, args);
		} else {
			invokeVoidMethod(targetClass, name, args);
		}
	}
}
