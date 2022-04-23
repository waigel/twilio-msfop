package com.klexhub.msfop.sipgate;

import com.klexhub.msfop.api.SMSProvider;
import com.klexhub.msfop.api.responses.CreateMessageResponse;
import com.klexhub.msfop.sipgate.models.CreateMessageRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.util.Date;

/*
 * SipgateProviderPlugin - Sipgate is a German SIP provider.
 * This integration is based on the Sipgate API V2.
 * You need a Sipgate account and a personal access token.
 */

public class SipgateProviderPlugin extends Plugin {

    private static final Logger logger = LoggerFactory.getLogger(SipgateProviderPlugin.class);
    private static final String BASE_URL = "https://api.sipgate.com/v2";


    public SipgateProviderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        logger.info("[SipgateProviderPlugin] Starting SipgateProviderPlugin (...)");
    }

    @Override
    public void stop() {
        logger.info("[SipgateProviderPlugin] Stopping SipgateProviderPlugin (...)");
    }

    @Extension
    public static class SipgateSMSProvider implements SMSProvider {

        @Override
        public CreateMessageResponse sendSMS(final String from, // this is here the smsId -> default "s0"
                                             final String to,
                                             final String body,
                                             final String accountSid,
                                             final String authToken) {

            logger.info("[SipgateSMSProvider] Sending SMS from {} to {} with body {}", from, to, body);
            final Date dateTimeBeforeRequestSend = new Date();

            HttpResponse<?> response = Unirest.post(BASE_URL + "/sessions/sms")
                .basicAuth(accountSid, authToken)
                .header("Content-Type", "application/json")
                .body(new CreateMessageRequest(from, to, body)).asEmpty();

            int httpStatus = response.getStatus();

            if (httpStatus != 204) {
                logger.error("[SipgateProvider] SMS request failed with status code: " + httpStatus);
            }

            /*
             * Unfortunately the Sipgate API does not return the smsId. So we need to fake an smsId.
             * But that can be a problem if you need to fetch the sms over the API.
             */
            final CreateMessageResponse createMessageResponse = new CreateMessageResponse();
            createMessageResponse.setAccount_sid(accountSid);
            createMessageResponse.setApi_version("v2");
            createMessageResponse.setBody(body);
            createMessageResponse.setDate_created(dateTimeBeforeRequestSend.toString());
            createMessageResponse.setDate_sent(new Date().toString());
            createMessageResponse.setDate_updated(new Date().toString());
            createMessageResponse.setDirection("outbound-api");
            createMessageResponse.setError_code(httpStatus == 204 ? null : "failed");
            createMessageResponse.setError_message(httpStatus == 204 ? null :
                httpStatus == 400 ? "Bad Request" : httpStatus == 402 ? "Insufficient funds" : "Error with status code: " + httpStatus);
            createMessageResponse.setFrom(from);
            createMessageResponse.setNum_media("0");
            createMessageResponse.setNum_segments("1");
            createMessageResponse.setPrice(null);
            createMessageResponse.setPrice_unit(null);
            createMessageResponse.setSid("SMS_" + new Date().getTime());
            createMessageResponse.setStatus(httpStatus == 204 ? "sent" : "failed");
            createMessageResponse.setSubresource_uris(null);
            createMessageResponse.setTo(to);
            createMessageResponse.setUri(null);
            return createMessageResponse;
        }
    }

}
