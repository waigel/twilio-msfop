package com.klexhub.msfop.sipgate;

import com.klexhub.msfop.api.models.CreateMessageRequest;
import com.klexhub.msfop.sipgate.models.SipgateHistoryItem;
import com.klexhub.msfop.sipgate.models.SipgateHistoryResponse;
import com.klexhub.msfop.sipgate.models.SipgateMessageResponse;
import com.klexhub.msfop.sipgate.utils.TwilioResponseMapper;
import com.twilio.rest.api.v2010.account.Message;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.klexhub.msfop.sipgate.SipgateProviderPlugin.PROVIDER_NAME;

public class SipgateClient {
    private static final Logger logger = LoggerFactory.getLogger(SipgateClient.class);

    private static final String BASE_URL = "https://api.sipgate.com/v2";


    public Optional<Message> sendSMS(final CreateMessageRequest sms, final String tokenId, final String token) {

        logger.info("[{}] Sending SMS from {} to {}: {}", PROVIDER_NAME, sms.getFrom(), sms.getTo(), sms.getBody());

        HttpResponse<?> response = Unirest.post(BASE_URL + "/sessions/sms")
                .basicAuth(tokenId, token)
                .header("Content-Type", "application/json")
                .body(new SipgateMessageResponse(sms.getFrom(), sms.getTo(), sms.getBody())).asEmpty();

        int httpStatus = response.getStatus();
        if (httpStatus != 204) {
            logger.error("[{}] SMS request failed with status code: {}", PROVIDER_NAME, httpStatus);
        }


        Optional<SipgateHistoryItem> historyItem = getHistoryItemForLatestCreatedSMS(sms, tokenId, token);
        logger.info("[{}] History item: {}", PROVIDER_NAME, historyItem);
        return historyItem.map(TwilioResponseMapper::mapToTwilioResponse);
    }

    private Optional<SipgateHistoryItem> getHistoryItemForLatestCreatedSMS(final CreateMessageRequest sms, final String tokenId, final String token) {
        HttpResponse<SipgateHistoryResponse> response = Unirest.get(BASE_URL + "/history")
                .queryString("types", "SMS")
                .queryString("directions", "OUTGOING")
                .queryString("phonenumber", sms.getTo())
                .queryString("archived", "false")
                .queryString("limit", "1")
                .basicAuth(tokenId, token)
                .header("Content-Type", "application/json")
                .asObject(SipgateHistoryResponse.class);

        int httpStatus = response.getStatus();
        if (httpStatus != 200) {
            logger.error("[{}] Failed to fetch history {}", PROVIDER_NAME, httpStatus);
        }
        return response.getBody().getItems().stream().filter(s -> s.getSmsContent().contentEquals(sms.getBody())).findFirst();
    }
}
