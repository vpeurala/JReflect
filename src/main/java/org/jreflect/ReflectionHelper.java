package org.jreflect;

import java.lang.reflect.Field;

public abstract class ReflectionHelper {
	@SuppressWarnings("unchecked")
	public static <T> T getValueFromField(Object targetObject, String fieldName) {
		try {
			Class<? extends Object> targetClass = targetObject.getClass();
			Field targetField = targetClass.getDeclaredField(fieldName);
			if (!targetField.isAccessible()) {
				targetField.setAccessible(true);
			}
			return (T) targetField.get(targetObject);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}
}
