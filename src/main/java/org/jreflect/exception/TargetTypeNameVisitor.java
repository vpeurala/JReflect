package org.jreflect.exception;


class TargetTypeNameVisitor implements TargetType.Visitor {
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