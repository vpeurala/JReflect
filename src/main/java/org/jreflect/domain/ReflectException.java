package org.jreflect.domain;

public class ReflectException extends RuntimeException {
    public enum Type {
        METHOD_NOT_FOUND_BY_NAME_FROM_OBJECT_INSTANCE
    }

    public ReflectException(final Type type, final Object targetObject) {
        super(buildMessage(type, targetObject));
    }

    public ReflectException(final Throwable cause) {
        super(cause);
    }

    private static String buildMessage(
            @SuppressWarnings("unused") final Type type,
            final Object targetObject) {
        return lines(
                "",
                "*** METHOD NOT FOUND BY NAME FROM OBJECT INSTANCE *** ",
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
