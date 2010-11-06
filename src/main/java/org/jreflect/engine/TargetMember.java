package org.jreflect.engine;

import org.jreflect.exception.InvocationType;

public class TargetMember implements VisitableWithTargetMemberVisitor {
    private final String memberName;
    private final Object object;
    private final Class<?> klass;

    public static TargetMember forObject(final String memberName,
            final Object object) {
        if (object instanceof Class<?>) {
            return forClass(memberName, (Class<?>) object);
        }
        return new TargetMember(memberName, object, null);
    }

    public static TargetMember forClass(final String memberName,
            final Class<?> klass) {
        return new TargetMember(memberName, null, klass);
    }

    private TargetMember(final String memberName, final Object object,
            final Class<?> klass) {
        this.memberName = memberName;
        this.object = object;
        this.klass = klass;
    }

    public TargetMethodWithoutReturnValue forMethodDiscardingReturnValue(
            final String methodName, final Object... arguments) {
        return new TargetMethodWithoutReturnValue(this, methodName, arguments);
    }

    public <ReturnType> TargetMethodWithReturnValue<ReturnType> forMethodKeepingReturnValue(
            final String methodName, final Class<ReturnType> returnType,
            final Object... arguments) {
        return new TargetMethodWithReturnValue<ReturnType>(this, methodName,
                returnType, arguments);
    }

    public <FieldType> TargetField<FieldType> forField(final String fieldName,
            final Class<FieldType> type) {
        return new TargetField<FieldType>(this, fieldName, type);
    }

    @Override
    public <ReturnType> ReturnType accept(
            final TargetMemberVisitor<ReturnType> v) {
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
        return accept(new TargetMemberVisitor<Class<?>>() {
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
        return accept(new TargetMemberVisitor<InvocationType>() {
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

    public String memberName() {
        return memberName;
    }
}
