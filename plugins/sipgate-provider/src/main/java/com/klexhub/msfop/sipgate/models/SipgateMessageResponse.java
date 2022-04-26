package com.klexhub.msfop.sipgate.models;

public class SipgateMessageResponse {
    public String smsId;
    public String recipient;
    public String message;

    public SipgateMessageResponse(final String smsId, final String recipient, final String message) {
        this.smsId = smsId;
        this.recipient = recipient;
        this.message = message;
    }
}
