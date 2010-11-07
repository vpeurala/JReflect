package org.jreflect.exceptions;

import static org.jreflect.Reflect.constructorOf;
import static org.jreflect.Reflect.method;
import static org.jreflect.util.StringUtil.lines;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.jreflect.exception.InvocationTargetReflectException;
import org.jreflect.exception.ReflectException;
import org.jreflect.exceptions.fixture.ClassWhichThrowsExceptions;
import org.junit.Test;

public class InvocationTargetExceptionTest {
    private final ClassWhichThrowsExceptions targetObject = new ClassWhichThrowsExceptions();

    @Test
    public void checkedExceptionFromInstanceMethodIsWrappedInJReflectException() {
        try {
            method("instanceMethodWhichThrowsCheckedException")
                    .in(targetObject).invoke();
            fail();
        } catch (final ReflectException e) {
            final Throwable cause = e.getCause();
            assertEquals(IOException.class, cause.getClass());
            assertEquals("foo!!!", cause.getMessage());
        }
    }

    @Test
    public void runtimeExceptionFromInstanceMethodIsPassedThrough() {
        try {
            method("instanceMethodWhichThrowsRuntimeException")
                    .in(targetObject).invoke();
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("foo!!!", e.getMessage());
        }
    }

    @Test
    public void errorFromInstanceMethodIsPassedThrough() {
        try {
            method("instanceMethodWhichThrowsError").in(targetObject).invoke();
            fail();
        } catch (final OutOfMemoryError e) {
            assertEquals("foo!!!", e.getMessage());
        }
    }

    @Test
    public void checkedExceptionFromStaticMethodIsWrappedInReflectInvocationTargetException() {
        try {
            method("staticMethodWhichThrowsCheckedException").in(
                    ClassWhichThrowsExceptions.class).invoke();
            fail();
        } catch (final ReflectException e) {
            final Throwable cause = e.getCause();
            assertEquals(IOException.class, cause.getClass());
            assertEquals("foo!!!", cause.getMessage());
            final String expectedErrorMessage = lines(
                    "org.jreflect.exception.InvocationTargetReflectException: ",
                    "Invocation raised a checked exception:",
                    "  java.io.IOException {",
                    "    \"foo!!!\"",
                    "      at org.jreflect.exceptions.fixture.ClassWhichThrowsExceptions.staticMethodWhichThrowsCheckedException(ClassWhichThrowsExceptions.java:40)",
                    "  }.\n");
            assertEquals(expectedErrorMessage, e.toString());
        }
    }

    @Test
    public void runtimeExceptionFromStaticMethodIsPassedThrough() {
        try {
            method("staticMethodWhichThrowsRuntimeException").in(
                    ClassWhichThrowsExceptions.class).invoke();
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("foo!!!", e.getMessage());
        }
    }

    @Test
    public void errorFromStaticMethodIsPassedThrough() {
        try {
            method("staticMethodWhichThrowsError").in(
                    ClassWhichThrowsExceptions.class).invoke();
            fail();
        } catch (final OutOfMemoryError e) {
            assertEquals("foo!!!", e.getMessage());
        }
    }

    @Test
    public void checkedExceptionFromConstructorIsWrappedInReflectInvocationTargetException() {
        try {
            constructorOf(ClassWhichThrowsExceptions.class).invoke(4);
            fail();
        } catch (final InvocationTargetReflectException e) {
            final Throwable cause = e.getCause();
            assertEquals(IOException.class, cause.getClass());
            assertEquals("4", cause.getMessage());
            final String expectedErrorMessage = lines(
                    "org.jreflect.exception.InvocationTargetReflectException: ",
                    "Invocation raised a checked exception:",
                    "  java.io.IOException {",
                    "    \"4\"",
                    "      at org.jreflect.exceptions.fixture.ClassWhichThrowsExceptions.<init>(ClassWhichThrowsExceptions.java:19)",
                    "  }.\n");
            assertEquals(expectedErrorMessage, e.toString());
        }
    }

    @Test
    public void runtimeExceptionFromConstructorIsPassedThrough() {
        try {
            constructorOf(ClassWhichThrowsExceptions.class).invoke("bar");
            fail();
        } catch (final IllegalArgumentException e) {
            assertEquals("bar", e.getMessage());
        }
    }

    @Test
    public void errorFromConstructorIsPassedThrough() {
        try {
            constructorOf(ClassWhichThrowsExceptions.class).invoke(true);
            fail();
        } catch (final OutOfMemoryError e) {
            assertEquals("true", e.getMessage());
        }
    }
}
