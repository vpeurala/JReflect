package org.jreflect.exceptions.fixture;

import java.io.IOException;

@SuppressWarnings("unused")
public class ClassWhichThrowsExceptions {
    /**
     * Nice constructor.
     */
    public ClassWhichThrowsExceptions() {
        super();
    }

    /**
     * Constructor which throws a checked exception.
     * @throws IOException
     */
    private ClassWhichThrowsExceptions(final int i) throws IOException {
        throw new IOException("" + i);
    }

    /**
     * Constructor which throws a checked exception.
     * @throws IllegalArgumentException
     */
    private ClassWhichThrowsExceptions(final String s) {
        throw new IllegalArgumentException(s);
    }

    /**
     * Constructor which throws an error.
     * @throws OutOfMemoryError
     */
    private ClassWhichThrowsExceptions(final boolean bool) {
        throw new OutOfMemoryError("" + bool);
    }

    private static void staticMethodWhichThrowsCheckedException()
            throws IOException {
        throw new IOException("foo!!!");
    }

    private static void staticMethodWhichThrowsRuntimeException() {
        throw new IllegalArgumentException("foo!!!");
    }

    private static void staticMethodWhichThrowsError() {
        throw new OutOfMemoryError("foo!!!");
    }

    private void instanceMethodWhichThrowsCheckedException() throws IOException {
        throw new IOException("foo!!!");
    }

    private void instanceMethodWhichThrowsRuntimeException() {
        throw new IllegalArgumentException("foo!!!");
    }

    private void instanceMethodWhichThrowsError() {
        throw new OutOfMemoryError("foo!!!");
    }
}
