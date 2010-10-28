package org.jreflect;

import static org.jreflect.ReflectionHelper.invokeMethod;

public class RMethodWithTarget {
	private final String name;
	private final Object targetObject;

	public RMethodWithTarget(String name, Object targetObject) {
		this.name = name;
		this.targetObject = targetObject;
	}

	public void invoke() {
		invokeMethod(targetObject, name);
	}
}
