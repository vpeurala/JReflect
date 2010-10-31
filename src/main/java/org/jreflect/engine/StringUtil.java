package org.jreflect.engine;

public class StringUtil {
    public static String lines(final Object... lines) {
        final StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i != 0) {
                buffer.append("\n");
            }
            buffer.append(lines[i].toString());
        }
        return buffer.toString();
    }
}
