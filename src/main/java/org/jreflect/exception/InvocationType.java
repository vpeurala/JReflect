package org.jreflect.exception;

public enum InvocationType {
    STATIC {
        @Override
        public String accept(final Visitor v) {
            return v.visitStatic();
        }
    },
    INSTANCE {
        @Override
        public String accept(final Visitor v) {
            return v.visitInstance();
        }
    };
    public abstract String accept(Visitor v);

    public interface Visitor {
        String visitStatic();

        String visitInstance();
    }
}