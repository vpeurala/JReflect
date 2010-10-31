package org.jreflect.engine;

public interface VisitableWithTargetVisitor {
    public <ReturnType> ReturnType accept(final TargetVisitor<ReturnType> v);
}
