package org.jreflect.exception;

public interface FailureTypeVisitor {
    String visitNotFoundByName();

    String visitNotFoundByMatchingParameters();

    String visitNotFoundByMatchingReturnType();
}