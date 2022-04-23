package com.klexhub.msfop.sipgate.models;

public class CreateMessageRequest {
    public String smsId;
    public String recipient;
    public String message;

    public CreateMessageRequest(final String smsId, final String recipient, final String message) {
        this.smsId = smsId;
        this.recipient = recipient;
        this.message = message;
    }
}
