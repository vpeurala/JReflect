package org.jreflect.exception;


class FailureTypeNameVisitor implements FailureTypeVisitor {
    @Override
    public String visitNotFoundByName() {
        return "NOT FOUND BY NAME";
    }

    @Override
    public String visitNotFoundByMatchingParameters() {
        return "NOT FOUND BY PARAMETERS";
    }

    @Override
    public String visitNotFoundByMatchingReturnType() {
        return "NOT FOUND BY RETURN TYPE";
    }
}