package org.jreflect.exception;

import java.util.HashMap;
import java.util.Map;

public class StackTraces extends ThreadLocal<Map<String, StackTraceElement[]>> {
    private static ThreadLocal<Map<String, StackTraceElement[]>> stackTraces = new ThreadLocal<Map<String, StackTraceElement[]>>() {
        @Override
        protected java.util.Map<String, StackTraceElement[]> initialValue() {
            return new HashMap<String, StackTraceElement[]>();
        }
    };

    public static StackTraceElement[] find(final String id) {
        return stackTraces.get().get(id);
    }

    public static void store(final String id) {
        stackTraces.get().put(id, new Exception().getStackTrace());
    }
}
