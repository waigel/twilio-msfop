package com.klexhub.msfop.controllers;

import com.klexhub.msfop.api.SMSProvider;
import com.klexhub.msfop.api.models.responses.CreateMessageResponse;
import com.klexhub.msfop.api.models.CreateMessageRequest;
import com.klexhub.msfop.providers.plugin.ProviderPluginLoader;
import com.klexhub.msfop.utils.BasicAuthHelper;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
public class CreateMessageController {

    private final ProviderPluginLoader providerPluginLoader;

    public CreateMessageController(ProviderPluginLoader providerPluginLoader) {
        this.providerPluginLoader = providerPluginLoader;
    }

    @PostMapping(
            path = "/2010-04-01/Accounts/{account_sid}/Messages.json",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Message> createMessage(final CreateMessageRequest createMessageRequest,
                                                               @RequestHeader("Authorization") final String authorizationHeader,
                                                               @PathVariable("account_sid") final String accountSid) {
        final SMSProvider smsProvider = providerPluginLoader.getSmsProvider();
        final String[] basicAuth = BasicAuthHelper.parse(authorizationHeader);

        final Optional<Message> messageResponse = smsProvider.sendSMS(
                createMessageRequest,
                accountSid,
                basicAuth
        );

        return messageResponse.map(message -> new ResponseEntity<>(message, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

    }
}
