package org.jreflect.exception;

public enum InvocationType {
    STATIC {
        @Override
        public String accept(final InvocationTypeVisitor v) {
            return v.visitStatic();
        }
    },
    INSTANCE {
        @Override
        public String accept(final InvocationTypeVisitor v) {
            return v.visitInstance();
        }
    };
    public abstract String accept(InvocationTypeVisitor v);
}