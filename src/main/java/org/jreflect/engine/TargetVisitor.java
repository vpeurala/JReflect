package org.jreflect.engine;

public interface TargetVisitor<ReturnType> {
    ReturnType visitObject(Object object);

    ReturnType visitClass(Class<?> klass);
}