package org.jreflect.jcql;

import java.util.ArrayList;
import java.util.List;

public class Codebase {
    private final List<Class<?>> classes = new ArrayList<Class<?>>();

    public Codebase(final List<Class<?>> classes) {
        this.classes.addAll(classes);
    }

    public JcqlResult jcql(final String jcqlString) {
        System.out.println(jcqlString);
        return new JcqlResult(classes);
    }
}
