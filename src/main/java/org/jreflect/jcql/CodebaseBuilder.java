package org.jreflect.jcql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CodebaseBuilder {
    private final List<File> directories = new ArrayList<File>();

    public CodebaseBuilder addDirectory(final String directoryName) {
        final File directory = new File(directoryName);
        if (directory.exists() && directory.isDirectory()) {
            directories.add(directory);
            return this;
        } else {
            throw new IllegalArgumentException(
                    "Directory does not exist or is not a directory: '"
                            + directoryName + "'.");
        }
    }

    public Codebase build() {
        final List<Class<?>> classes = new ArrayList<Class<?>>();
        for (final File directory : directories) {
            collectClasses(directory, classes);
        }
        return new Codebase(classes);
    }

    private void collectClasses(final File directory,
            final List<Class<?>> classes) {
        for (final File entry : directory.listFiles()) {
            // Skip files beginning with dot
            if (entry.getName().startsWith(".")) {
                continue;
            }
            // Do not load inner classes
            if (entry.isFile() && entry.getName().endsWith(".class")
                    && !entry.getName().contains("$")) {
                try {
                    classes.add(getClass().getClassLoader().loadClass(
                            javaBinaryNameFromFile(entry)));
                    System.out.println("Loaded class from file "
                            + entry.getName());
                } catch (final ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (entry.isDirectory()) {
                collectClasses(entry, classes);
            }
        }
    }

    private String javaBinaryNameFromFile(final File entry) {
        if (isUnderAKnownClasspathRoot(entry)) {
            return entry
                    .getAbsolutePath()
                    .substring(
                            knownClassPathRootFor(entry).getAbsolutePath()
                                    .length()).replace('/', '.').substring(1)
                    .replaceFirst(".class$", "");
        } else {
            throw new RuntimeException(entry.getAbsolutePath());
        }
    }

    private File knownClassPathRootFor(final File entry) {
        if (isUnderAKnownClasspathRoot(entry)) {
            return new File("...");
        } else {
            throw new RuntimeException(entry.getAbsolutePath());
        }
    }

    private boolean isUnderAKnownClasspathRoot(final File entry) {
        return entry.getAbsolutePath().startsWith("...");
    }
}
