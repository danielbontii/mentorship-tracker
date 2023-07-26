package com.danielbbontii.mentorshiptracker.converters;

import com.danielbbontii.mentorshiptracker.enums.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserType userType) {
        if (userType == null) {
            return null;
        }
        return userType.getTypeNumber();
    }

    @Override
    public UserType convertToEntityAttribute(Integer typeNumber) {
        if (typeNumber == null) {
            return null;
        }
        return Stream.of(UserType.values())
                .filter(type -> type.getTypeNumber() == typeNumber)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
