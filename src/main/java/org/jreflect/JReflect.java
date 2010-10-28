package org.jreflect;

public class JReflect {
	public static RField field(String name) {
		return new RField(name);
	}

	public static RMethod method(String name) {
		return new RMethod(name);
	}
}
