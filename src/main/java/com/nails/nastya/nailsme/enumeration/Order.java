package com.nails.nastya.nailsme.enumeration;

import lombok.Getter;

@Getter
public enum Order {
    APPOINT_RESERVE,
    APPOINT_RESERVE_CANCELLING,
    APPOINT_RESERVE_RESCHEDULING,
    CONTACT_MASTER;
}
