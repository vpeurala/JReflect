package org.jreflect.exception;

public enum TargetType {
    FIELD {
        @Override
        public String accept(final TargetTypeVisitor v) {
            return v.visitField();
        }
    },
    METHOD {
        @Override
        public String accept(final TargetTypeVisitor v) {
            return v.visitMethod();
        }
    },
    CONSTRUCTOR {
        @Override
        public String accept(final TargetTypeVisitor v) {
            return v.visitConstructor();
        }
    };
    public abstract String accept(TargetTypeVisitor v);
}