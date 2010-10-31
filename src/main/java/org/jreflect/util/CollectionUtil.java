package org.jreflect.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtil {
    @SuppressWarnings("unchecked")
    public static <InputItemType, OutputItemType, InputCollectionType extends Collection<InputItemType>, OutputCollectionType extends Collection<OutputItemType>> OutputCollectionType map(
            final InputCollectionType input,
            final Transformer<InputItemType, OutputItemType> transformer) {
        final OutputCollectionType output;
        if (input instanceof List<?>) {
            output = (OutputCollectionType) new ArrayList<OutputItemType>();
        } else if (input instanceof Set<?>) {
            output = (OutputCollectionType) new HashSet<OutputItemType>();
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported collection type: " + input.getClass());
        }
        for (final InputItemType item : input) {
            output.add(transformer.transform(item));
        }
        return output;
    }

    public static interface Transformer<InputItemType, OutputItemType> {
        public OutputItemType transform(InputItemType input);
    }
}
