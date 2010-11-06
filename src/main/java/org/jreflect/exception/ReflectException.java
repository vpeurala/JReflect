package org.jreflect.exception;

import static org.jreflect.engine.Methods.allMethodsOfClass;
import static org.jreflect.util.CollectionUtil.map;
import static org.jreflect.util.StringUtil.join;
import static org.jreflect.util.StringUtil.lines;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.jreflect.engine.TargetMember;
import org.jreflect.util.CollectionUtil.Transformer;

public class ReflectException extends RuntimeException {
    public ReflectException(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target) {
        super(buildMessage(targetType, invocationType, failureType, target));
    }

    protected ReflectException(final Throwable cause) {
        super("\nInvocation raised a checked exception:\n  "
                + cause.getClass().getName() + " {\n" + "    \""
                + cause.getMessage() + "\"\n"
                + formatStackTrace(cause.getStackTrace()) + "  }.\n", cause);
    }

    private static String formatStackTrace(final StackTraceElement[] stackTrace) {
        final StringBuilder buffer = new StringBuilder();
        final StackTraceElement frame = stackTrace[0];
        buffer.append("      at " + frame.toString());
        buffer.append('\n');
        return buffer.toString();
    }

    private static String buildMessage(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target) {
        return lines(
                "",
                "*** "
                        + targetType.accept(new TargetTypeNameVisitor())
                                .toUpperCase()
                        + " "
                        + failureType.accept(new FailureTypeNameVisitor())
                        + " "
                        + "FROM "
                        + invocationType
                                .accept(new InvocationTypeFromVisitor())
                        + " ***",
                "",
                "REASON FOR THIS EXCEPTION:",
                "--------------------------",
                failureType.accept(new FailureType.Visitor() {
                    @Override
                    public String visitNotFoundByName() {
                        return lines(
                                "There is no "
                                        + targetType
                                                .accept(new TargetTypeNameVisitor())
                                        + " named", "  '" + target.memberName()
                                        + "'");
                    }

                    @Override
                    public String visitNotFoundByMatchingReturnType() {
                        return lines(
                                "There is a "
                                        + targetType
                                                .accept(new TargetTypeNameVisitor())
                                        + " with name",
                                "  '" + target.memberName() + "'",
                                "and its parameters match",
                                // TODO VP Show the parameters
                                "but its return type",
                                // FIXME VP Hardcoded shite
                                "  (void)",
                                "does not match the given return type",
                                "  (String)");
                    }

                    @Override
                    public String visitNotFoundByMatchingParameters() {
                        return lines(
                                "There is a "
                                        + targetType
                                                .accept(new TargetTypeNameVisitor())
                                        + " with name",
                                "  '" + target.memberName() + "'",
                                "but its parameters",
                                // FIXME VP Hardcoded shite
                                "  (long, double, String)",
                                "do not match given parameters",
                                "  (String \"foo\")");
                    }
                }), "in target object of class",
                "  'org.jreflect.methods.fixture.ClassWithInstanceMethods'.",
                "", formatMethodsOfClassHierarchy(target.targetClass()), "",
                "TARGET OBJECT:", "--------------", target.targetObject()
                        .toString(), "\n");
    }

    private static String formatMethodsOfClassHierarchy(final Class<?> klass) {
        return lines("EXISTING METHODS IN THE TARGET CLASS HIERARCHY:",
                "-----------------------------------------------",
                "Methods declared in the class of the target object", "("
                        + klass.getName() + ")", formatMethodsOfClass(klass),
                "", formatMethodsOfSuperclasses(klass));
    }

    private static String formatMethodsOfSuperclasses(
            final Class<?> originalClass) {
        final StringBuilder buffer = new StringBuilder();
        Class<?> currentClass = originalClass;
        while (!currentClass.getSuperclass().equals(Object.class)) {
            buffer.append(lines("Methods declared in the superclass of '"
                    + currentClass.getSimpleName() + "'", "("
                    + currentClass.getSuperclass().getName() + ")",
                    formatMethodsOfClass(currentClass.getSuperclass()), ""));
            buffer.append("\n");
            currentClass = currentClass.getSuperclass();
        }
        buffer.append("Superclass of '" + currentClass.getSimpleName()
                + "' is java.lang.Object.");
        return buffer.toString();
    }

    private static String formatMethodsOfClass(final Class<?> klass) {
        return join(
                map(allMethodsOfClass(klass),
                        new Transformer<Method, String>() {
                            @Override
                            public String transform(final Method inputMethod) {
                                return formatMethod(inputMethod);
                            }
                        }), "\n");
    }

    private static String formatMethod(final Method inputMethod) {
        return "   + " + Modifier.toString(inputMethod.getModifiers()) + " "
                + inputMethod.getReturnType().getSimpleName() + " "
                + inputMethod.getName() + "("
                + formatParameterTypes(inputMethod.getParameterTypes()) + ")";
    }

    private static String formatParameterTypes(final Class<?>[] parameterTypes) {
        return join(
                map(Arrays.<Class<?>> asList(parameterTypes),
                        new Transformer<Class<?>, String>() {
                            @Override
                            public String transform(final Class<?> inputType) {
                                return inputType.getSimpleName();
                            }
                        }), ", ");
    }
}
