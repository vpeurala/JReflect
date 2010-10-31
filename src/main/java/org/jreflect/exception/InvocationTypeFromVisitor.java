package org.jreflect.exception;


class InvocationTypeFromVisitor implements InvocationType.Visitor {
    @Override
    public String visitStatic() {
        return "CLASS";
    }

    @Override
    public String visitInstance() {
        return "OBJECT INSTANCE";
    }
}