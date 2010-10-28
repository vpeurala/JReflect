package org.jreflect;

import static java.util.Arrays.asList;

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

	public static void invokeVoidMethod(Class<?> targetClass,
			String methodName, Object... args) {
		Method targetMethod = getAccessibleMethod(targetClass, methodName, args);
		try {
			targetMethod.invoke(null, args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static void invokeVoidMethod(Object targetObject, String methodName,
			Object... args) {
		Method targetMethod = getAccessibleMethod(targetObject, methodName,
				args);
		try {
			targetMethod.invoke(targetObject, args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <ReturnType> ReturnType invokeValueReturningMethod(
			Object targetObject, String methodName,
			Class<ReturnType> returnType, Object... args) {
		Method targetMethod = getAccessibleMethod(targetObject, methodName,
				args);
		try {
			return (ReturnType) targetMethod.invoke(targetObject, args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <ReturnType> ReturnType invokeValueReturningMethod(
			Class<?> targetClass, String methodName,
			Class<ReturnType> returnType, Object... args) {
		Method targetMethod = getAccessibleMethod(targetClass, methodName, args);
		try {
			return (ReturnType) targetMethod.invoke(null, args);
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
			String methodName, Object[] args) {
		return getAccessibleMethod(targetObject.getClass(), methodName, args);
	}

	private static Method getAccessibleMethod(Class<?> targetClass,
			String methodName, Object[] args) {
		Arguments arguments = new Arguments(args);
		Method targetMethod = null;
		try {
			for (Method m : targetClass.getDeclaredMethods()) {
				if (methodName.equals(m.getName())
						&& arguments.matchesMethod(m)) {
					targetMethod = m;
					break;
				}
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		if (targetMethod == null) {
			throw new RuntimeException("There is no method named '"
					+ methodName + "' with arguments compatible with "
					+ arguments + " in target class " + targetClass
					+ ". Methods tried: "
					+ asList(targetClass.getDeclaredMethods()));
		}
		if (!targetMethod.isAccessible()) {
			targetMethod.setAccessible(true);
		}
		return targetMethod;
	}
}
