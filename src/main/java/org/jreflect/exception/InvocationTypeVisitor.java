package org.jreflect.exception;

public interface InvocationTypeVisitor {
    String visitStatic();

    String visitInstance();
}