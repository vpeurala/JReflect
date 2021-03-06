package org.jreflect.methods.statics;

import org.jreflect.methods.AbstractMethodTestCase;
import org.jreflect.methods.fixture.ClassWithStaticMethods;

public class StaticMethodTest extends AbstractMethodTestCase<Class<?>> {
    @Override
    protected Class<?> target() {
        return ClassWithStaticMethods.class;
    }

    @Override
    protected String methodPrefix() {
        return "method";
    }
}
