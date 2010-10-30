package org.jreflect.engine;

import java.lang.reflect.AccessibleObject;

public abstract class AllMembers {
    public static void setAccessible(final AccessibleObject accessibleObject) {
        if (!accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
    }
}
