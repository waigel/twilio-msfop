package com.klexhub.msfop.sipgate.models;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SipgateHistoryItem {
    public String id;
    public String source;
    public String target;
    public String sourceAlias;
    public String targetAlias;
    public String type;
    public Date created;
    public Date lastModified;
    public String direction;
    public boolean incoming;
    public String status;
    public ArrayList<String> connectionIds;
    public boolean read;
    public boolean archived;
    public Object note;
    public ArrayList<SipgateEndpoint> endpoints;
    public boolean starred;
    public ArrayList<Object> labels;
    public String smsContent;
    public String scheduled;
}
