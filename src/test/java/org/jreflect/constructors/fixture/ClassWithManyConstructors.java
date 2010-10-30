package org.jreflect.constructors.fixture;

public class ClassWithManyConstructors {
    private final byte b;
    private final short s;
    private final char c;
    private final int i;
    private final long l;
    private final float f;
    private final double d;
    private final boolean bool;
    private final Object o;

    private ClassWithManyConstructors() {
        b = 0;
        s = 1;
        c = 2;
        i = 3;
        l = 4;
        f = 5;
        d = 6;
        bool = false;
        o = null;
    }

    private ClassWithManyConstructors(final byte b, final short s,
            final char c, final int i, final long l, final float f,
            final double d, final boolean bool, final Object o) {
        this.b = b;
        this.s = s;
        this.c = c;
        this.i = i;
        this.l = l;
        this.f = f;
        this.d = d;
        this.bool = bool;
        this.o = o;
    }

    @Override
    public String toString() {
        return "ClassWithManyConstructors [b=" + b + ", s=" + s + ", c=" + c
                + ", i=" + i + ", l=" + l + ", f=" + f + ", d=" + d + ", bool="
                + bool + ", o=" + o + "]";
    }
}
