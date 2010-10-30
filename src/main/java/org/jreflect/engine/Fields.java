package org.jreflect.engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fields {
    @SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(final Class<?> targetClass,
            final String fieldName) {
        return (T) getStaticFieldValue(getAccessibleFieldOfClass(targetClass,
                fieldName));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object targetObject,
            final String fieldName) {
        return (T) getFieldValue(targetObject,
                getAccessibleFieldOfObject(targetObject, fieldName));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(final Field targetField) {
        return (T) getFieldValue(null, targetField);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object targetObject,
            final Field targetField) {
        try {
            return (T) targetField.get(targetObject);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldValue(final Object targetObject,
            final String fieldName, final Object value) {
        setFieldValue(targetObject,
                getAccessibleFieldOfObject(targetObject, fieldName), value);
    }

    public static void setStaticFieldValue(final Class<?> targetClass,
            final String fieldName, final Object value) {
        setFieldValue(null, getAccessibleFieldOfClass(targetClass, fieldName),
                value);
    }

    public static void setFieldValue(final Object targetObject,
            final Field targetField, final Object value) {
        try {
            targetField.set(targetObject, value);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Field getAccessibleFieldOfObject(final Object targetObject,
            final String fieldName) {
        return getAccessibleFieldOfClass(targetObject.getClass(), fieldName);
    }

    public static Field getAccessibleFieldOfClass(final Class<?> targetClass,
            final String fieldName) {
        final List<Field> fields = allFieldsOfClassAndSuperclasses(targetClass);
        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                AllMembers.setAccessible(field);
                return field;
            }
        }
        throw new RuntimeException("No field named '" + fieldName
                + "' found on class " + targetClass
                + " or its superclass hierarchy.");
    }

    public static List<Field> allFieldsOfClassAndSuperclasses(
            final Class<?> targetClass) {
        final List<Field> fields = new ArrayList<Field>();
        Class<?> currentClass = targetClass;
        while (currentClass != null) {
            fields.addAll(Arrays.<Field> asList(currentClass
                    .getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }
}
