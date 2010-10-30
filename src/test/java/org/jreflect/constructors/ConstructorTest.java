package org.jreflect.constructors;

import static org.jreflect.JReflect.constructorOf;
import static org.junit.Assert.assertNotNull;

import org.jreflect.constructors.fixture.ClassWithManyConstructors;
import org.junit.Test;

public class ConstructorTest {
    @Test
    public void canInvokeConstructorWithNoArgs() {
        final ClassWithManyConstructors instance = constructorOf(
                ClassWithManyConstructors.class).invoke();
        assertNotNull(instance);
    }
}
