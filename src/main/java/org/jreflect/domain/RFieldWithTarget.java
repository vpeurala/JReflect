package org.jreflect.domain;

import static org.jreflect.engine.Fields.getFieldValue;
import static org.jreflect.engine.Fields.getStaticFieldValue;
import static org.jreflect.engine.Fields.setFieldValue;
import static org.jreflect.engine.Fields.setStaticFieldValue;

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
