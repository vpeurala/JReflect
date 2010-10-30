package org.jreflect.domain;

import static org.jreflect.engine.Fields.getFieldValue;
import static org.jreflect.engine.Fields.getStaticFieldValue;
import static org.jreflect.engine.Fields.setFieldValue;
import static org.jreflect.engine.Fields.setStaticFieldValue;

public class RFieldWithTargetAndType<T> {
    private final String name;
    private final Object targetObject;
    private final Class<?> targetClass;

    public RFieldWithTargetAndType(final String name, final Class<?> targetClass) {
        this(name, null, targetClass);
    }

    public RFieldWithTargetAndType(final String name, final Object targetObject) {
        this(name, targetObject, null);
    }

    private RFieldWithTargetAndType(final String name,
            final Object targetObject, final Class<?> targetClass) {
        this.name = name;
        this.targetObject = targetObject;
        this.targetClass = targetClass;
    }

    @SuppressWarnings("unchecked")
    public T getValue() {
        if (targetObject != null) {
            return (T) getFieldValue(targetObject, name);
        } else {
            return (T) getStaticFieldValue(targetClass, name);
        }
    }

    public void setValue(final T value) {
        if (targetObject != null) {
            setFieldValue(targetObject, name, value);
        } else {
            setStaticFieldValue(targetClass, name, value);
        }
    }
}
