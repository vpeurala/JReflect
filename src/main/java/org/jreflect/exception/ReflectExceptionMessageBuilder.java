package org.jreflect.exception;

import static org.jreflect.engine.Methods.allMethodsOfClass;
import static org.jreflect.util.CollectionUtil.map;
import static org.jreflect.util.StringUtil.join;
import static org.jreflect.util.StringUtil.lines;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.jreflect.engine.TargetMember;
import org.jreflect.util.CollectionUtil.Transformer;

class ReflectExceptionMessageBuilder {
    private final TargetType targetType;
    private final InvocationType invocationType;
    private final FailureType failureType;
    private final TargetMember target;
    private final Member closestMatch;

    public ReflectExceptionMessageBuilder(final TargetType targetType,
            final InvocationType invocationType, final FailureType failureType,
            final TargetMember target, final Member closestMatch) {
        this.targetType = targetType;
        this.invocationType = invocationType;
        this.failureType = failureType;
        this.target = target;
        this.closestMatch = closestMatch;
    }

    public String buildMessage() {
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
                failureType.accept(new FailureTypeVisitor() {
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
                    public String visitNotFoundByMatchingParameters() {
                        return lines(
                                "There is a "
                                        + targetType
                                                .accept(new TargetTypeNameVisitor())
                                        + " with name",
                                "  '" + target.memberName() + "'",
                                "but its parameters",
                                "  ("
                                        + formatParameterTypes(((Method) closestMatch)
                                                .getParameterTypes()) + ")",
                                "do not match given parameters",
                                "  (String \"foo\")");
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
                }), "in " + invocationType.accept(new InvocationTypeVisitor() {
                    @Override
                    public String visitStatic() {
                        return "target class";
                    }

                    @Override
                    public String visitInstance() {
                        return "target object of class";
                    }
                }), "  '" + target.targetClass().getName() + "'.", "",
                formatMethodsOfClassHierarchy(target.targetClass()),
                invocationType.accept(new InvocationTypeVisitor() {
                    @Override
                    public String visitStatic() {
                        return "";
                    }

                    @Override
                    public String visitInstance() {
                        return lines("", "TARGET OBJECT:", "--------------",
                                target.targetObject().toString());
                    }
                }), "\n");
    }

    private String formatMethodsOfClassHierarchy(final Class<?> klass) {
        return lines(
                "EXISTING METHODS IN THE TARGET CLASS HIERARCHY:",
                "-----------------------------------------------",
                "Methods declared in the "
                        + invocationType.accept(new InvocationTypeVisitor() {
                            @Override
                            public String visitInstance() {
                                return "class of the target object";
                            }

                            @Override
                            public String visitStatic() {
                                return "target class";
                            }
                        }), "(" + klass.getName() + ")",
                formatMethodsOfClass(klass), "",
                formatMethodsOfSuperclasses(klass));
    }

    private String formatMethodsOfSuperclasses(final Class<?> originalClass) {
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

    private String formatMethodsOfClass(final Class<?> klass) {
        return join(
                map(allMethodsOfClass(klass),
                        new Transformer<Method, String>() {
                            @Override
                            public String transform(final Method inputMethod) {
                                return formatMethod(inputMethod);
                            }
                        }), "\n");
    }

    private String formatMethod(final Method inputMethod) {
        return "   + " + Modifier.toString(inputMethod.getModifiers()) + " "
                + inputMethod.getReturnType().getSimpleName() + " "
                + inputMethod.getName() + "("
                + formatParameterTypes(inputMethod.getParameterTypes()) + ")";
    }

    private String formatParameterTypes(final Class<?>[] parameterTypes) {
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