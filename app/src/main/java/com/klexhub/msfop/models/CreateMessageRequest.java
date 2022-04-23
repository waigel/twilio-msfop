package com.klexhub.msfop.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateMessageRequest {
    private String From;
    private String To;
    private String Body;
}
