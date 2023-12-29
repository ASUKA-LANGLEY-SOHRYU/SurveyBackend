package com.prosvirnin.alphabackend.model.survey.filter;


import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EnumToStringConverter<E extends Enum<E>> implements AttributeConverter<List<E>, String> {

    private static final String SPLIT_CHAR = ",";
    private Class<E> enumClass;

    public EnumToStringConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(List<E> attribute) {
        return attribute != null ? String
                .join(SPLIT_CHAR,
                        attribute.stream()
                                .map(x -> "" + x.ordinal())
                                .toList()
                        ) : null;
    }

    @Override
    public List<E> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return null; // Arrays.stream(dbData.split(SPLIT_CHAR))
               // .map(Integer::valueOf)
                //.map(E::)
                //.collect(Collectors.toList());
    }
}