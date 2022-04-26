package com.klexhub.msfop.sipgate.utils;

import com.klexhub.msfop.sipgate.models.SipgateHistoryItem;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.ZonedDateTime;

public class TwilioResponseMapper {

    public static Message mapToTwilioResponse(final SipgateHistoryItem sms) {
        return Message.creator(new PhoneNumber(sms.getTarget()),
                        new PhoneNumber(sms.getSource()), sms.getSmsContent())
                .setBody(sms.getSmsContent())
                .setScheduleType(sms.getScheduled() != null ? Message.ScheduleType.FIXED : null)
                .setSendAt(sms.getScheduled() == null ? ZonedDateTime.now() : ZonedDateTime.parse(sms.getScheduled()))
                .setMessagingServiceSid(sms.getId()).create();
    }
}
