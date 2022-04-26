package com.klexhub.msfop.sipgate.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SipgateEndpoint {
    public String type;
    public String extension;
    public SipgateEndpoint endpoint;
}
