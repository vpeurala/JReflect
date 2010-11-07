package org.jreflect.exception;

public interface TargetTypeVisitor {
    String visitField();

    String visitMethod();

    String visitConstructor();
}