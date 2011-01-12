package org.jreflect.jcql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JcqlTest {
    @Test
    public void canReadCodebase() {
        final Codebase codebase = new CodebaseBuilder().addDirectory(
                "/home/vpeurala/Projects/ELISA/omaelisa-full-2").build();
        assertEquals(-1, codebase.jcql("Classes").count());
    }
}
