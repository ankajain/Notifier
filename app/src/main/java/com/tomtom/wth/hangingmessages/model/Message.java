package com.tomtom.wth.hangingmessages.model;

import androidx.annotation.NonNull;

import com.tomtom.wth.hangingmessages.enums.MessageType;
import com.tomtom.wth.hangingmessages.enums.PriorityType;
import com.tomtom.wth.hangingmessages.util.Util;

public final class Message {

    private final long id;
    private final String sender;
    private final String to;
    private final String subject;
    private final MessageType type;
    private final PriorityType priority;
    private final String content;
    private final long createdOn;
    private final long validFrom;
    private final long validTo;

    public Message(long id, @NonNull String sender, @NonNull String to, @NonNull String subject,
                   @NonNull MessageType type, @NonNull PriorityType priority,
                   @NonNull String content, long createdOn, long validFrom, long validTo) {
        super();
        this.id = id;
        this.sender = sender;
        this.to = to;
        this.subject = subject;
        this.type = type;
        this.priority = priority;
        this.content = content;
        this.createdOn = createdOn;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public MessageType getType() {
        return type;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public String getContent() {
        return content;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public long getValidFrom() {
        return validFrom;
    }

    public long getValidTo() {
        return validTo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", type=" + type +
                ", priority=" + priority +
                ", content='" + content + '\'' +
                ", createdOn=" + createdOn +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }

    public static class MessageBuilder {
        private long id;
        private String sender;
        private String to;
        private String subject;
        private MessageType type;
        private PriorityType priority;
        private String content;
        private int day;
        private int month;
        private int year;
        private int hour;
        private int minute;
        private int validForDays;
        private int validForHours;


        public MessageBuilder id(long id) {
            this.id = id;
            return this;
        }

        public MessageBuilder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public MessageBuilder to(String to) {
            this.to = to;
            return this;
        }

        public MessageBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MessageBuilder type(MessageType type) {
            this.type = type;
            return this;
        }

        public MessageBuilder priority(PriorityType priority) {
            this.priority = priority;
            return this;
        }

        public MessageBuilder messageContent(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder validFromDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
            return this;
        }

        public MessageBuilder validFromTime(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
            return this;
        }

        public MessageBuilder validFor(int days, int hours) {
            validForDays = days;
            validForHours = hours;
            return this;
        }

        public Message build() {
            long createdOn = System.currentTimeMillis();
            long validFrom = Util.convert2Timestamp(year, month, day, hour, minute);
            long validTo = Util.addDuration2Date(validFrom, 0, 0, validForDays, validForHours, 0);
            return new Message(id, sender, to, subject, type, priority, content, createdOn, validFrom, validTo);
        }
    }
}
