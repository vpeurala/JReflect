package org.jreflect.engine;

abstract class Option<T> {
    private Option() {
        // Hidden.
    }

    static <T> Option<T> some(final T value) {
        return new Some<T>(value);
    }

    static <T> Option<T> none() {
        return new None<T>();
    }

    abstract T getValue();

    abstract boolean isSome();

    final boolean isNone() {
        return !isSome();
    }

    static class Some<T> extends Option<T> {
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

    static class None<T> extends Option<T> {
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