package com.example.ticketbooker.Util.Enum;

public enum BusType {
    BEDSEAT("Bed Seat"),
    SEAT("Seat");

    private final String displayName;

    BusType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
