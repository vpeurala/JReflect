package org.jreflect.exception;

public enum FailureType {
    NOT_FOUND_BY_NAME {
        @Override
        public String accept(final FailureTypeVisitor v) {
            return v.visitNotFoundByName();
        }
    },
    NOT_FOUND_BY_MATCHING_PARAMETERS {
        @Override
        public String accept(final FailureTypeVisitor v) {
            return v.visitNotFoundByMatchingParameters();
        }
    },
    NOT_FOUND_BY_MATCHING_RETURN_TYPE {
        @Override
        public String accept(final FailureTypeVisitor v) {
            return v.visitNotFoundByMatchingReturnType();
        }
    };
    public abstract String accept(FailureTypeVisitor v);
}