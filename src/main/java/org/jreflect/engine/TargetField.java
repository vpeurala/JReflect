package org.jreflect.engine;

import java.lang.reflect.Field;
import java.util.List;

import org.jreflect.domain.ReflectException;
import org.jreflect.domain.ReflectException.FailureType;
import org.jreflect.domain.ReflectException.TargetType;

public class TargetField<Type> {
    private final Target target;
    private final String fieldName;
    private final Class<Type> type;
    private final Field targetField;

    TargetField(final Target target, final String fieldName,
            final Class<Type> type) {
        this.target = target;
        this.fieldName = fieldName;
        this.type = type;
        targetField = findField();
    }

    private Field findField() {
        final List<Field> fields = Fields
                .allFieldsOfClassAndSuperclasses(target.targetClass());
        for (final Field field : fields) {
            if (field.getName().equals(fieldName)
                    && field.getType().equals(type)) {
                return field;
            }
        }
        throw reflectException();
    }

    public void setValue(final Type value) {
        try {
            targetField.set(target.targetObject(), value);
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ReflectException reflectException() {
        return new ReflectException(TargetType.FIELD, target.invocationType(),
                FailureType.NOT_FOUND_BY_NAME, target);
    }
}
