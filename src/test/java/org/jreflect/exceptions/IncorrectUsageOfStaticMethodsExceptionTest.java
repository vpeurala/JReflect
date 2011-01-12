package org.jreflect.exceptions;

import static org.jreflect.Reflect.method;
import static org.jreflect.util.StringUtil.lines;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jreflect.exception.ReflectException;
import org.jreflect.methods.fixture.ClassWithStaticMethods;
import org.junit.Test;

public class IncorrectUsageOfStaticMethodsExceptionTest {
    @Test
    public void niceErrorMessageFromStaticMethodNotFoundByName() {
        try {
            method("methodWhichDoesNotExist").in(ClassWithStaticMethods.class)
                    .invoke();
            fail();
        } catch (final ReflectException e) {
            final String expectedErrorMessage = lines(
                    "",
                    "*** STATIC METHOD NOT FOUND BY NAME FROM CLASS ***",
                    "",
                    "REASON FOR THIS EXCEPTION:",
                    "--------------------------",
                    "There is no method named",
                    "  'methodWhichDoesNotExist'",
                    "in target class",
                    "  'org.jreflect.methods.fixture.ClassWithStaticMethods'.",
                    "",
                    hierarchyOfClassWithStaticMethods(),
                    "Are you sure you intended to invoke method in(<Class<?> || Object>)",
                    "  at org.jreflect.exceptions.IncorrectUsageOfStaticMethodsExceptionTest.niceErrorMessageFromStaticMethodNotFoundByName(IncorrectUsageOfStaticMethodsExceptionTest.java:16)",
                    "with a class (org.jreflect.methods.fixture.ClassWithStaticMethods) instead of an object instance?",
                    "\n");
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void niceErrorMessageFromIncorrectNumberOfParamsOnFoundStaticMethod() {
        try {
            method("methodWithParametersAndReturnValue").in(
                    ClassWithStaticMethods.class).invoke("foo");
            fail();
        } catch (final ReflectException e) {
            final String expectedErrorMessage = lines(
                    "",
                    "*** STATIC METHOD NOT FOUND BY PARAMETERS FROM CLASS ***",
                    "",
                    "REASON FOR THIS EXCEPTION:",
                    "--------------------------",
                    "There is a method with name",
                    "  'methodWithParametersAndReturnValue'",
                    "but its parameters",
                    "  (long, double, String)",
                    "do not match given parameters",
                    "  (String \"foo\")",
                    "in target class",
                    "  'org.jreflect.methods.fixture.ClassWithStaticMethods'.",
                    "",
                    hierarchyOfClassWithStaticMethods(),
                    "Are you sure you intended to invoke method in(<Class<?> || Object>)",
                    "  at org.jreflect.exceptions.IncorrectUsageOfStaticMethodsExceptionTest.niceErrorMessageFromIncorrectNumberOfParamsOnFoundStaticMethod(IncorrectUsageOfStaticMethodsExceptionTest.java:43)",
                    "with a class (org.jreflect.methods.fixture.ClassWithStaticMethods) instead of an object instance?",
                    "\n");
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void niceErrorMessageFromIncorrectReturnTypeOnFoundStaticMethod() {
        try {
            method("methodWithParametersAndReturnValue")
                    .withReturnType(String.class)
                    .in(ClassWithStaticMethods.class).invoke(1, 2, "gurp");
            fail();
        } catch (final ReflectException e) {
            final String expectedErrorMessage = lines(
                    "",
                    "*** STATIC METHOD NOT FOUND BY RETURN TYPE FROM CLASS ***",
                    "",
                    "REASON FOR THIS EXCEPTION:",
                    "--------------------------",
                    "There is a method with name",
                    "  'methodWithParametersAndReturnValue'",
                    "and its parameters match",
                    "but its return type",
                    "  (void)",
                    "does not match the given return type",
                    "  (String)",
                    "in target class",
                    "  'org.jreflect.methods.fixture.ClassWithStaticMethods'.",
                    "",
                    hierarchyOfClassWithStaticMethods(),
                    "Are you sure you intended to invoke method in(<Class<?> || Object>)",
                    "  at org.jreflect.exceptions.IncorrectUsageOfStaticMethodsExceptionTest.niceErrorMessageFromIncorrectReturnTypeOnFoundStaticMethod(IncorrectUsageOfStaticMethodsExceptionTest.java:76)",
                    "with a class (org.jreflect.methods.fixture.ClassWithStaticMethods) instead of an object instance?",
                    "\n");
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    private String hierarchyOfClassWithStaticMethods() {
        return lines(
                "EXISTING STATIC METHODS IN THE TARGET CLASS HIERARCHY:",
                "-----------------------------------------------",
                "Methods declared in the target class",
                "(org.jreflect.methods.fixture.ClassWithStaticMethods)",
                "   + private static void methodWithNoParametersAndNoReturnValue()",
                "   + private static int methodWithNoParametersAndReturnValue()",
                "   + private static void methodWithParametersAndNoReturnValue(String, int, boolean)",
                "   + private static Integer methodWithParametersAndReturnValue(long, double, String)",
                "",
                "Methods declared in the superclass of 'ClassWithStaticMethods'",
                "(org.jreflect.methods.fixture.SuperclassWithStaticMethods)",
                "   + private static void superclassMethodWithNoParametersAndNoReturnValue()",
                "   + private static int superclassMethodWithNoParametersAndReturnValue()",
                "   + private static void superclassMethodWithParametersAndNoReturnValue(String, int, boolean)",
                "   + private static Integer superclassMethodWithParametersAndReturnValue(long, double, String)",
                "",
                "Superclass of 'SuperclassWithStaticMethods' is java.lang.Object.",
                "");
    }
}
