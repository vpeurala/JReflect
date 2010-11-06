package org.jreflect.exceptions;

import static org.jreflect.Reflect.constructorOf;
import static org.jreflect.Reflect.method;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jreflect.exception.ReflectException;
import org.jreflect.exception.ReflectInvocationTargetException;
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
            assertEquals(
                    expectedErrorMessage("checkedExceptionFromStaticMethodIsWrappedInReflectInvocationTargetException.txt"),
                    e.toString());
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
        } catch (final ReflectInvocationTargetException e) {
            final Throwable cause = e.getCause();
            assertEquals(IOException.class, cause.getClass());
            assertEquals("4", cause.getMessage());
            assertEquals(
                    expectedErrorMessage("checkedExceptionFromConstructorIsWrappedInReflectInvocationTargetException.txt"),
                    e.toString());
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

    private String expectedErrorMessage(final String name) {
        final InputStream stream = getClass().getResourceAsStream(name);
        if (stream == null) {
            throw new RuntimeException("Resource not found: '" + name + "'.");
        }
        final InputStreamReader reader = new InputStreamReader(stream);
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final StringBuilder buffer = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            bufferedReader.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return buffer.toString();
    }
}
