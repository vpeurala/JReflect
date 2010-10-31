package org.jreflect.util;

import java.util.Collection;

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

    public static String join(final Collection<?> input, final String separator) {
        final StringBuilder buffer = new StringBuilder();
        int i = 0;
        for (final Object item : input) {
            if (i != 0) {
                buffer.append(separator);
            }
            buffer.append(item.toString());
            i++;
        }
        return buffer.toString();
    }
}
