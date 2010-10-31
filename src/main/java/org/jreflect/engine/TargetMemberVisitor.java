package org.jreflect.engine;

public interface TargetMemberVisitor<ReturnType> {
    ReturnType visitObject(Object object);

    ReturnType visitClass(Class<?> klass);
}