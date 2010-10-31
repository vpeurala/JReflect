package org.jreflect.exception;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;
import static org.jreflect.engine.Methods.allMethodsOfClass;
import static org.jreflect.util.CollectionUtil.map;
import static org.jreflect.util.StringUtil.join;
import static org.jreflect.util.StringUtil.lines;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jreflect.engine.TargetMember;
import org.jreflect.util.CollectionUtil.Transformer;

public class ReflectException extends RuntimeException {
    public ReflectException(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target) {
        super(buildMessage(targetType, invocationType, failureType, target));
    }

    public ReflectException(final Throwable cause) {
        super(cause);
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
                        + " *** ",
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
                        // FIXME Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "visitNotFoundByMatchingReturnType");
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
                                return "   + "
                                        + formatModifiers(inputMethod
                                                .getModifiers())
                                        + " "
                                        + inputMethod.getReturnType()
                                                .getSimpleName()
                                        + " "
                                        + inputMethod.getName()
                                        + "("
                                        + formatParameterTypes(inputMethod
                                                .getParameterTypes()) + ")";
                            }

                            private String formatModifiers(final int modifiers) {
                                final List<String> keywords = new ArrayList<String>();
                                if (isPublic(modifiers)) {
                                    keywords.add("public");
                                } else if (isProtected(modifiers)) {
                                    keywords.add("protected");
                                } else if (isPrivate(modifiers)) {
                                    keywords.add("private");
                                }
                                if (Modifier.isStatic(modifiers)) {
                                    keywords.add("static");
                                }
                                return join(keywords, " ");
                            }

                            private String formatParameterTypes(
                                    final Class<?>[] parameterTypes) {
                                return join(
                                        map(Arrays
                                                .<Class<?>> asList(parameterTypes),
                                                new Transformer<Class<?>, String>() {
                                                    @Override
                                                    public String transform(
                                                            final Class<?> inputType) {
                                                        return inputType
                                                                .getSimpleName();
                                                    }
                                                }), ", ");
                            }
                        }), "\n");
    }
}
