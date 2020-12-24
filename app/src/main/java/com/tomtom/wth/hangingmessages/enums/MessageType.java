package com.tomtom.wth.hangingmessages.enums;


public enum MessageType {
    INFORMATION(0),
    ADVERTISEMENT(1),
    INSTRUCTION(2),
    WARNING(3);

    private final int sId;

    private MessageType(int id) {
        this.sId = id;
    }

    public int getId() {
        return sId;
    }

    public static MessageType getById(int id) {
        for (MessageType type : values()) {
            if (type.sId == id)
                return type;
        }
        throw new IllegalArgumentException("Incorrect message type id");
    }
}
