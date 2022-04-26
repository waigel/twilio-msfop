package com.klexhub.msfop.sipgate.models;


import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SipgateHistoryResponse{
    public ArrayList<SipgateHistoryItem> items;
    public int totalCount;
}