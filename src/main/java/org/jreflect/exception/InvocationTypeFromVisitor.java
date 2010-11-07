package org.jreflect.exception;


class InvocationTypeFromVisitor implements InvocationTypeVisitor {
    @Override
    public String visitStatic() {
        return "CLASS";
    }

    @Override
    public String visitInstance() {
        return "OBJECT INSTANCE";
    }
}