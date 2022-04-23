package com.klexhub.msfop.api.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageResponse{
    public String account_sid;
    public String api_version;
    public String body;
    public String date_created;
    public String date_sent;
    public String date_updated;
    public String direction;
    public Object error_code;
    public Object error_message;
    public String from;
    public String messaging_service_sid;
    public String num_media;
    public String num_segments;
    public Object price;
    public Object price_unit;
    public String sid;
    public String status;
    public CreateMessageSubresourceUrisResponse subresource_uris;
    public String to;
    public String uri;
}
