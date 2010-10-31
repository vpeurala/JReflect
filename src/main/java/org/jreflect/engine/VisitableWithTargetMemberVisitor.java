package org.jreflect.engine;

public interface VisitableWithTargetMemberVisitor {
    public <ReturnType> ReturnType accept(
            final TargetMemberVisitor<ReturnType> v);
}
