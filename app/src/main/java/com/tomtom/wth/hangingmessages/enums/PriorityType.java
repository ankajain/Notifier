package com.tomtom.wth.hangingmessages.enums;


public enum PriorityType {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final int sId;

    private PriorityType(int id) {
        this.sId = id;
    }

    public int getId() {
        return sId;
    }

    public static PriorityType getById(int id) {
        for (PriorityType type : values()) {
            if (type.sId == id)
                return type;
        }
        throw new IllegalArgumentException("Incorrect priority type id");
    }
}
