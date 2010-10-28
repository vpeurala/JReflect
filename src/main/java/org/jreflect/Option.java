package org.jreflect;

public abstract class Option<T> {
    private Option() {
        // Hidden.
    }

    public static <T> Option<T> some(final T value) {
        return new Some<T>(value);
    }

    public static <T> Option<T> none() {
        return new None<T>();
    }

    public abstract T getValue();

    public abstract boolean isSome();

    public final boolean isNone() {
        return !isSome();
    }

    public static class Some<T> extends Option<T> {
        private final T value;

        public Some(final T value) {
            this.value = value;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public boolean isSome() {
            return true;
        }
    }

    public static class None<T> extends Option<T> {
        @Override
        public T getValue() {
            throw new UnsupportedOperationException(
            "Option.None has no value, so you cannot invoke getValue() on it!");
        }

        @Override
        public boolean isSome() {
            return false;
        }
    }
}