package com.nails.nastya.nailsme.enumeration;

public enum SpecialityKind {
    MANICURE("маникюр"),
    PEDICURE("педикюр"),
    NAILS_CURE("лечение ногтей");

    private String name;

    SpecialityKind(String name) {
        this.name = name;
    }
}
