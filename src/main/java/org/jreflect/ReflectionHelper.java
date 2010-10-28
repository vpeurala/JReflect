package org.jreflect;

import java.lang.reflect.Field;

public abstract class ReflectionHelper {
	@SuppressWarnings("unchecked")
	public static <T> T getValueFromField(Object targetObject, String fieldName) {
		try {
			Field targetField = getAccessibleField(targetObject, fieldName);
			return (T) targetField.get(targetObject);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void setValueOnField(Object targetObject, String fieldName,
			Object value) {
		Field targetField = getAccessibleField(targetObject, fieldName);
		try {
			targetField.set(targetObject, value);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static Field getAccessibleField(Object targetObject,
			String fieldName) {
		Class<? extends Object> targetClass = targetObject.getClass();
		Field targetField;
		try {
			targetField = targetClass.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		if (!targetField.isAccessible()) {
			targetField.setAccessible(true);
		}
		return targetField;
	}
}
