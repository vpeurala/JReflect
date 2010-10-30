package org.jreflect.exceptions;

import static org.jreflect.Reflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jreflect.domain.ReflectException;
import org.jreflect.methods.fixture.ClassWithInstanceMethods;
import org.junit.Ignore;
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
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    // FIXME VP Continue here
    @Ignore
    @Test
    public void niceErrorMessageFromIncorrectNumberOfParamsOnFoundInstanceMethod() {
        final ClassWithInstanceMethods targetObject = new ClassWithInstanceMethods();
        //try {
        method("methodWithParametersAndReturnValue").in(targetObject).invoke(
                "foo");
        fail();
        //        } catch (final ReflectException e) {
        //            final String expectedErrorMessage = lines(
        //                    "",
        //                    "*** METHOD NOT FOUND BY NAME FROM OBJECT INSTANCE *** ",
        //                    "",
        //                    "REASON FOR THIS EXCEPTION:",
        //                    "--------------------------",
        //                    "There is no method named",
        //                    "  'methodWhichDoesNotExist'",
        //                    "in target object of class",
        //                    "  'org.jreflect.methods.fixture.ClassWithInstanceMethods'.",
        //                    "",
        //                    "EXISTING METHODS IN THE TARGET CLASS HIERARCHY:",
        //                    "-----------------------------------------------",
        //                    "",
        //                    "Methods declared in the class of the target object",
        //                    "(org.jreflect.methods.fixture.ClassWithInstanceMethods)",
        //                    "   + private void methodWithNoParametersAndNoReturnValue()",
        //                    "   + private int methodWithNoParametersAndReturnValue()",
        //                    "   + private void methodWithParametersAndNoReturnValue(String, int, boolean)",
        //                    "   + private Integer methodWithParametersAndReturnValue(long, double, String)",
        //                    "",
        //                    "Methods declared in the superclass of 'ClassWithInstanceMethods'"
        //                            + "(org.jreflect.methods.fixture.SuperclassWithInstanceMethods)",
        //                    "   + private void superclassMethodWithNoParametersAndNoReturnValue()",
        //                    "   + private int superclassMethodWithNoParametersAndReturnValue()",
        //                    "   + private void superclassMethodWithParametersAndNoReturnValue(String, int, boolean)",
        //                    "   + private Integer superclassMethodWithParametersAndReturnValue(long, double, String)",
        //                    "",
        //                    "Superclass of 'SuperclassWithInstanceMethods' is java.lang.Object.",
        //                    "", "TARGET OBJECT:", "--------------", "",
        //                    targetObject.toString(), "");
        //            assertEquals(expectedErrorMessage, e.getMessage());
        //        }
    }

    private String lines(final Object... lines) {
        final StringBuilder buffer = new StringBuilder();
        for (final Object line : lines) {
            buffer.append(line.toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
