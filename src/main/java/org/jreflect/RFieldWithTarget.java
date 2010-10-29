package org.jreflect;

import static org.jreflect.ReflectionEngine.getFieldValue;
import static org.jreflect.ReflectionEngine.getStaticFieldValue;
import static org.jreflect.ReflectionEngine.setFieldValue;
import static org.jreflect.ReflectionEngine.setStaticFieldValue;

public class RFieldWithTarget {
    private final String name;
    private final Object targetObject;
    private final Class<?> targetClass;

    public RFieldWithTarget(final String name, final Class<?> targetClass) {
        this(name, null, targetClass);
    }

    public RFieldWithTarget(final String name, final Object targetObject) {
        this(name, targetObject, null);
    }

    private RFieldWithTarget(final String name, final Object targetObject,
            final Class<?> targetClass) {
        this.name = name;
        this.targetObject = targetObject;
        this.targetClass = targetClass;
    }

    public Object getValue() {
        if (targetObject != null) {
            return getFieldValue(targetObject, name);
        } else {
            return getStaticFieldValue(targetClass, name);
        }
    }

    public void setValue(final Object value) {
        if (targetObject != null) {
            setFieldValue(targetObject, name, value);
        } else {
            setStaticFieldValue(targetClass, name, value);
        }
    }
}
