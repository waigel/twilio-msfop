package com.klexhub.msfop.api;

import com.klexhub.msfop.api.models.CreateMessageRequest;
import com.klexhub.msfop.api.models.responses.CreateMessageResponse;
import com.twilio.rest.api.v2010.account.Message;
import org.pf4j.ExtensionPoint;

import java.util.Optional;

public interface SMSProvider extends ExtensionPoint {
    Optional<Message> sendSMS(final CreateMessageRequest createMessageRequest, final String accountSid, final String[] auth);
}
