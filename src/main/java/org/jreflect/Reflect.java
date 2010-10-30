package org.jreflect;

import org.jreflect.domain.RConstructor;
import org.jreflect.domain.RField;
import org.jreflect.domain.RMethod;


public abstract class Reflect {
    public static RField field(final String name) {
        return new RField(name);
    }

    public static RMethod method(final String name) {
        return new RMethod(name);
    }

    public static <T> RConstructor<T> constructorOf(final Class<T> targetClass) {
        return new RConstructor<T>(targetClass);
    }
}
