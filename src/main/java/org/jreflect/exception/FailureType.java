package org.jreflect.exception;

public enum FailureType {
    NOT_FOUND_BY_NAME {
        @Override
        public String accept(final Visitor v) {
            return v.visitNotFoundByName();
        }
    },
    NOT_FOUND_BY_MATCHING_PARAMETERS {
        @Override
        public String accept(final Visitor v) {
            return v.visitNotFoundByMatchingParameters();
        }
    },
    NOT_FOUND_BY_MATCHING_RETURN_TYPE {
        @Override
        public String accept(final Visitor v) {
            return v.visitNotFoundByMatchingReturnType();
        }
    };
    public abstract String accept(Visitor v);

    public interface Visitor {
        String visitNotFoundByName();

        String visitNotFoundByMatchingParameters();

        String visitNotFoundByMatchingReturnType();
    }
}