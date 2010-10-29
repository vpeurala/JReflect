package org.jreflect.methods.inheritance;

import org.jreflect.methods.AbstractMethodTestCase;
import org.jreflect.methods.fixture.ClassWithStaticMethods;

public class StaticMethodOnSuperclassTest extends
        AbstractMethodTestCase<Class<?>> {
    @Override
    protected Class<?> target() {
        return ClassWithStaticMethods.class;
    }

    @Override
    protected String methodPrefix() {
        return "superclassMethod";
    }
}
