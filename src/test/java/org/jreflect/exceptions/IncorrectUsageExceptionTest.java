package org.jreflect.exceptions;

import static org.jreflect.Reflect.method;
import static org.jreflect.util.StringUtil.lines;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jreflect.exception.ReflectException;
import org.jreflect.methods.fixture.ClassWithInstanceMethods;
import org.junit.Test;

public class IncorrectUsageExceptionTest {
    @Test
    public void niceErrorMessageFromInstanceMethodNotFoundByName() {
        final ClassWithInstanceMethods targetObject = new ClassWithInstanceMethods();
        try {
            method("methodWhichDoesNotExist").in(targetObject).invoke();
            fail();
        } catch (final ReflectException e) {
            final String expectedErrorMessage = lines(
                    "",
                    "*** METHOD NOT FOUND BY NAME FROM OBJECT INSTANCE ***",
                    "",
                    "REASON FOR THIS EXCEPTION:",
                    "--------------------------",
                    "There is no method named",
                    "  'methodWhichDoesNotExist'",
                    "in target object of class",
                    "  'org.jreflect.methods.fixture.ClassWithInstanceMethods'.",
                    "", hierarchyOfClassWithInstanceMethods(),
                    "TARGET OBJECT:", "--------------",
                    targetObject.toString(), "\n");
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void niceErrorMessageFromIncorrectNumberOfParamsOnFoundInstanceMethod() {
        final ClassWithInstanceMethods targetObject = new ClassWithInstanceMethods();
        try {
            method("methodWithParametersAndReturnValue").in(targetObject)
                    .invoke("foo");
            fail();
        } catch (final ReflectException e) {
            final String expectedErrorMessage = lines(
                    "",
                    "*** METHOD NOT FOUND BY PARAMETERS FROM OBJECT INSTANCE ***",
                    "",
                    "REASON FOR THIS EXCEPTION:",
                    "--------------------------",
                    "There is a method with name",
                    "  'methodWithParametersAndReturnValue'",
                    "but its parameters",
                    "  (long, double, String)",
                    "do not match given parameters",
                    "  (String \"foo\")",
                    "in target object of class",
                    "  'org.jreflect.methods.fixture.ClassWithInstanceMethods'.",
                    "", hierarchyOfClassWithInstanceMethods(),
                    "TARGET OBJECT:", "--------------",
                    targetObject.toString(), "\n");
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void niceErrorMessageFromIncorrectReturnTypeOnFoundInstanceMethod() {
        final ClassWithInstanceMethods targetObject = new ClassWithInstanceMethods();
        try {
            method("methodWithParametersAndReturnValue")
                    .withReturnType(String.class).in(targetObject)
                    .invoke(1, 2, "gurp");
            fail();
        } catch (final ReflectException e) {
            final String expectedErrorMessage = lines(
                    "",
                    "*** METHOD NOT FOUND BY RETURN TYPE FROM OBJECT INSTANCE ***",
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
                    "in target object of class",
                    "  'org.jreflect.methods.fixture.ClassWithInstanceMethods'.",
                    "", hierarchyOfClassWithInstanceMethods(),
                    "TARGET OBJECT:", "--------------",
                    targetObject.toString(), "\n");
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    private String hierarchyOfClassWithInstanceMethods() {
        return lines(
                "EXISTING METHODS IN THE TARGET CLASS HIERARCHY:",
                "-----------------------------------------------",
                "Methods declared in the class of the target object",
                "(org.jreflect.methods.fixture.ClassWithInstanceMethods)",
                "   + private void methodWithNoParametersAndNoReturnValue()",
                "   + private int methodWithNoParametersAndReturnValue()",
                "   + private void methodWithParametersAndNoReturnValue(String, int, boolean)",
                "   + private Integer methodWithParametersAndReturnValue(long, double, String)",
                "",
                "Methods declared in the superclass of 'ClassWithInstanceMethods'",
                "(org.jreflect.methods.fixture.SuperclassWithInstanceMethods)",
                "   + private void superclassMethodWithNoParametersAndNoReturnValue()",
                "   + private int superclassMethodWithNoParametersAndReturnValue()",
                "   + private void superclassMethodWithParametersAndNoReturnValue(String, int, boolean)",
                "   + private Integer superclassMethodWithParametersAndReturnValue(long, double, String)",
                "",
                "Methods declared in the superclass of 'SuperclassWithInstanceMethods'",
                "(org.jreflect.methods.fixture.Hyperclass)",
                "   + protected boolean isHyper()", "",
                "Superclass of 'Hyperclass' is java.lang.Object.", "");
    }
}
