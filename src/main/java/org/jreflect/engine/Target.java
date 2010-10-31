package org.jreflect.engine;

import org.jreflect.domain.ReflectException.InvocationType;

public class Target implements VisitableWithTargetVisitor {
    private final Object object;
    private final Class<?> klass;

    public static Target forObject(final Object object) {
        if (object instanceof Class<?>) {
            return forClass((Class<?>) object);
        }
        return new Target(object, null);
    }

    public static Target forClass(final Class<?> klass) {
        return new Target(null, klass);
    }

    private Target(final Object object, final Class<?> klass) {
        this.object = object;
        this.klass = klass;
    }

    public <ReturnType> TargetMethod<ReturnType> forMethod(
            final String methodName, final Class<ReturnType> returnType,
            final Object... arguments) {
        return new TargetMethod<ReturnType>(this, methodName, returnType,
                arguments);
    }

    public <FieldType> TargetField<FieldType> forField(final String fieldName,
            final Class<FieldType> type) {
        return new TargetField<FieldType>(this, fieldName, type);
    }

    @Override
    public <ReturnType> ReturnType accept(final TargetVisitor<ReturnType> v) {
        if (object != null) {
            return v.visitObject(object);
        } else {
            return v.visitClass(klass);
        }
    }

    public Object targetObject() {
        return object;
    }

    public Class<?> targetClass() {
        return accept(new TargetVisitor<Class<?>>() {
            @Override
            public Class<?> visitObject(final Object targetObject) {
                return targetObject.getClass();
            }

            @Override
            public Class<?> visitClass(final Class<?> targetClass) {
                return targetClass;
            }
        });
    }

    public InvocationType invocationType() {
        return accept(new TargetVisitor<InvocationType>() {
            @Override
            public InvocationType visitObject(final Object targetObject) {
                return InvocationType.INSTANCE;
            }

            @Override
            public InvocationType visitClass(final Class<?> targetClass) {
                return InvocationType.STATIC;
            }
        });
    }
}
