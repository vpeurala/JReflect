package org.jreflect.exception;


class TargetTypeNameVisitor implements TargetTypeVisitor {
    @Override
    public String visitMethod() {
        return "method";
    }

    @Override
    public String visitField() {
        return "field";
    }

    @Override
    public String visitConstructor() {
        return "constructor";
    }
}