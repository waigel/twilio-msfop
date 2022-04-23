package com.klexhub.msfop.api;

import com.klexhub.msfop.api.responses.CreateMessageResponse;
import org.pf4j.ExtensionPoint;

public interface SMSProvider extends ExtensionPoint {
    CreateMessageResponse sendSMS(final String from, final String to, final String body, final String accountSid, final String authToken);
}
