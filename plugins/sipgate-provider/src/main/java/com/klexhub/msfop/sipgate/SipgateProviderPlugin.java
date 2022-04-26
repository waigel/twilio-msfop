package com.klexhub.msfop.sipgate;

import com.klexhub.msfop.api.SMSProvider;
import com.klexhub.msfop.api.models.CreateMessageRequest;
import com.klexhub.msfop.api.models.responses.CreateMessageResponse;
import com.klexhub.msfop.sipgate.models.SipgateHistoryItem;
import com.klexhub.msfop.sipgate.models.SipgateMessageResponse;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.util.Date;
import java.util.Optional;

/*
 * SipgateProviderPlugin - Sipgate is a German SIP provider.
 * This integration is based on the Sipgate API V2.
 * You need a Sipgate account and a personal access token.
 */

public class SipgateProviderPlugin extends Plugin {

    private static final Logger logger = LoggerFactory.getLogger(SipgateProviderPlugin.class);
    public static final String PROVIDER_NAME = "SipgateProvider";
    private static SipgateClient client;


    public SipgateProviderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        logger.info("[{}] Starting {} (...)", PROVIDER_NAME, PROVIDER_NAME);
        client = new SipgateClient();
    }

    @Override
    public void stop() {
        logger.info("[{}] Stopping {} (...)", PROVIDER_NAME, PROVIDER_NAME);
    }

    @Extension
    public static class SipgateSMSProvider implements SMSProvider {
        @Override
        public Optional<Message> sendSMS(final CreateMessageRequest sms, String accountSid, String[] auth) {
            logger.info("[{}] Sending SMS from {} to {} with body {}", PROVIDER_NAME, sms.getFrom(), sms.getTo(), sms.getBody());
            final Optional<Message> message =  client.sendSMS(sms,auth[0],auth[1]);
            return message;
        }
    }
}
