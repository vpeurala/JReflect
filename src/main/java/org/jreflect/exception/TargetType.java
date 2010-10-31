package org.jreflect.exception;

public enum TargetType {
    FIELD {
        @Override
        public String accept(final Visitor v) {
            return v.visitField();
        }
    },
    METHOD {
        @Override
        public String accept(final Visitor v) {
            return v.visitMethod();
        }
    },
    CONSTRUCTOR {
        @Override
        public String accept(final Visitor v) {
            return v.visitConstructor();
        }
    };
    public abstract String accept(Visitor v);

    public interface Visitor {
        String visitField();

        String visitMethod();

        String visitConstructor();
    }
}