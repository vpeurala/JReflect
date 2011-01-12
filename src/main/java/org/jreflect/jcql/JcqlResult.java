package org.jreflect.jcql;

import java.util.ArrayList;
import java.util.List;

public class JcqlResult {
    private final List<Class<?>> classes = new ArrayList<Class<?>>();

    public JcqlResult(final List<Class<?>> classes) {
        this.classes.addAll(classes);
    }

    public int count() {
        return classes.size();
    }
}
