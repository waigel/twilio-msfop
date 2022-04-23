package com.klexhub.msfop.controllers;

import com.klexhub.msfop.api.SMSProvider;
import com.klexhub.msfop.api.responses.CreateMessageResponse;
import com.klexhub.msfop.models.CreateMessageRequest;
import com.klexhub.msfop.providers.plugin.ProviderPluginLoader;
import com.klexhub.msfop.utils.BasicAuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.websocket.server.PathParam;

@Controller
public class CreateMessageController {

    @Autowired
    private ProviderPluginLoader providerPluginLoader;


    @PostMapping(
        path = "/2010-04-01/Accounts/{account_sid}/Messages.json",
        consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<CreateMessageResponse> createMessage(@PathParam("account_sid") final String accountSid,
                                                               final CreateMessageRequest createMessageRequest,
                                                               @RequestHeader("Authorization") final String authorizationHeader) {
        final SMSProvider smsProvider =  providerPluginLoader.getSmsProvider();
        final String[] auth = BasicAuthHelper.parse(authorizationHeader);

        final CreateMessageResponse createMessageResponse = smsProvider.sendSMS(
            createMessageRequest.getFrom(),
            createMessageRequest.getTo(),
            createMessageRequest.getBody(),
            auth.length > 0 ? auth[0] : accountSid,
            authorizationHeader.length() > 1 ? auth[1] : null
        );

        return new ResponseEntity<CreateMessageResponse>(createMessageResponse,
            createMessageResponse.getError_code() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
}
