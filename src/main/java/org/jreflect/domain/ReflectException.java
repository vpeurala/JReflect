package org.jreflect.domain;

public class ReflectException extends RuntimeException {
    public enum TargetType {
        FIELD {
            @Override
            public String accept(final Visitor v) {
                return v.visitField();
            }
        },
        METHOD {
            @Override
            public String accept(final Visitor v) {
                return v.visitMethod();
            }
        },
        CONSTRUCTOR {
            @Override
            public String accept(final Visitor v) {
                return v.visitConstructor();
            }
        };
        public abstract String accept(Visitor v);

        public interface Visitor {
            String visitField();

            String visitMethod();

            String visitConstructor();
        }
    }

    public enum InvocationType {
        STATIC {
            @Override
            public String accept(final Visitor v) {
                return v.visitStatic();
            }
        },
        INSTANCE {
            @Override
            public String accept(final Visitor v) {
                return v.visitInstance();
            }
        };
        public abstract String accept(Visitor v);

        public interface Visitor {
            String visitStatic();

            String visitInstance();
        }
    }

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

    public ReflectException(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final Object targetObject) {
        super(buildMessage(targetType, invocationType, failureType,
                targetObject));
    }

    public ReflectException(final Throwable cause) {
        super(cause);
    }

    private static String buildMessage(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final Object targetObject) {
        return lines(
                "",
                "*** " + targetType.accept(new TargetType.Visitor() {
                    @Override
                    public String visitMethod() {
                        return "METHOD";
                    }

                    @Override
                    public String visitField() {
                        return "FIELD";
                    }

                    @Override
                    public String visitConstructor() {
                        return "CONSTRUCTOR";
                    }
                }) + " " + failureType.accept(new FailureType.Visitor() {
                    @Override
                    public String visitNotFoundByName() {
                        return "NOT FOUND BY NAME";
                    }

                    @Override
                    public String visitNotFoundByMatchingParameters() {
                        return "NOT FOUND BY INPUT PARAMETERS";
                    }

                    @Override
                    public String visitNotFoundByMatchingReturnType() {
                        return "NOT FOUND BY RETURN TYPE";
                    }
                }) + " " + "FROM "
                        + invocationType.accept(new InvocationType.Visitor() {
                            @Override
                            public String visitStatic() {
                                return "CLASS";
                            }

                            @Override
                            public String visitInstance() {
                                return "OBJECT INSTANCE";
                            }
                        }) + " *** ",
                "",
                "REASON FOR THIS EXCEPTION:",
                "--------------------------",
                "There is no method named",
                "  'methodWhichDoesNotExist'",
                "in target object of class",
                "  'org.jreflect.methods.fixture.ClassWithInstanceMethods'.",
                "",
                "EXISTING METHODS IN THE TARGET CLASS HIERARCHY:",
                "-----------------------------------------------",
                "",
                "Methods declared in the class of the target object",
                "(org.jreflect.methods.fixture.ClassWithInstanceMethods)",
                "   + private void methodWithNoParametersAndNoReturnValue()",
                "   + private int methodWithNoParametersAndReturnValue()",
                "   + private void methodWithParametersAndNoReturnValue(String, int, boolean)",
                "   + private Integer methodWithParametersAndReturnValue(long, double, String)",
                "",
                "Methods declared in the superclass of 'ClassWithInstanceMethods'"
                        + "(org.jreflect.methods.fixture.SuperclassWithInstanceMethods)",
                "   + private void superclassMethodWithNoParametersAndNoReturnValue()",
                "   + private int superclassMethodWithNoParametersAndReturnValue()",
                "   + private void superclassMethodWithParametersAndNoReturnValue(String, int, boolean)",
                "   + private Integer superclassMethodWithParametersAndReturnValue(long, double, String)",
                "",
                "Superclass of 'SuperclassWithInstanceMethods' is java.lang.Object.",
                "", "TARGET OBJECT:", "--------------", "",
                targetObject.toString(), "");
    }

    private static String lines(final Object... lines) {
        final StringBuilder buffer = new StringBuilder();
        for (final Object line : lines) {
            buffer.append(line.toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
