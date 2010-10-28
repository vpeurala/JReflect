package org.jreflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

	public static void invokeMethod(Object targetObject, String methodName) {
		Method targetMethod = getAccessibleMethod(targetObject, methodName);
		try {
			targetMethod.invoke(targetObject);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <ReturnType> ReturnType invokeMethod(Object targetObject,
			String methodName, Class<ReturnType> returnType) {
		Method targetMethod = getAccessibleMethod(targetObject, methodName);
		try {
			return (ReturnType) targetMethod.invoke(targetObject);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
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

	private static Method getAccessibleMethod(Object targetObject,
			String methodName) {
		Class<? extends Object> targetClass = targetObject.getClass();
		Method targetMethod;
		try {
			targetMethod = targetClass.getDeclaredMethod(methodName);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		if (!targetMethod.isAccessible()) {
			targetMethod.setAccessible(true);
		}
		return targetMethod;
	}
}
