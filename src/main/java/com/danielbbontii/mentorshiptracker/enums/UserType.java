package com.danielbbontii.mentorshiptracker.enums;

public enum UserType {

    ADMIN(1), ADVISOR(2), ADVISEE(3);

    private final int typeNumber;

    UserType(int typeNumber) {
        this.typeNumber = typeNumber;
    }

    public int getTypeNumber() {
        return typeNumber;
    }
}
