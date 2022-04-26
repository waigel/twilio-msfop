package com.klexhub.msfop.api.models;

import com.twilio.rest.api.v2010.account.Message;
import lombok.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateMessageRequest {
    private String pathAccountSid;
    private String to;
    private String from;
    private String messagingServiceSid;
    private String body;
    private List<URI> mediaUrl;
    private URI statusCallback;
    private String applicationSid;
    private BigDecimal maxPrice;
    private Boolean provideFeedback;
    private Integer attempt;
    private Integer validityPeriod;
    private Boolean forceDelivery;
    private Message.ContentRetention contentRetention;
    private Message.AddressRetention addressRetention;
    private Boolean smartEncoded;
    private List<String> persistentAction;
    private Message.ScheduleType scheduleType;
    private ZonedDateTime sendAt;
    private Boolean sendAsMms;
}
