package org.jreflect.constructors;

import static org.jreflect.Reflect.constructorOf;
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

    @Test
    public void canInvokeConstructorWithArgs() {
        final ClassWithManyConstructors instance = constructorOf(
                ClassWithManyConstructors.class).invoke((byte) 1, (short) 2,
                (char) 3, 4, 5, 6, 7, true, "foo");
        assertNotNull(instance);
    }
}
