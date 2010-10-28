package org.jreflect;

import static org.jreflect.ReflectionEngine.getFieldValue;
import static org.jreflect.ReflectionEngine.setFieldValue;

public class RFieldWithTarget {
    private final String name;
    private final Object targetObject;

    public RFieldWithTarget(final String name, final Object targetObject) {
        this.name = name;
        this.targetObject = targetObject;
    }

    public Object getValue() {
        return getFieldValue(targetObject, name);
    }

    public void setValue(final Object value) {
        setFieldValue(targetObject, name, value);
    }
}
