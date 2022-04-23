/*
 * Copyright (C) 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.klexhub.msfop;

import com.klexhub.msfop.api.Greeting;
import com.klexhub.msfop.providers.plugin.ProviderPluginLoader;
import org.apache.commons.lang3.StringUtils;
import org.pf4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.List;

/**
 * A boot class that start the demo.
 *
 * @author Decebal Suiu
 */
@SpringBootApplication
public class Boot {
    private static final Logger logger = LoggerFactory.getLogger(Boot.class);
    @Autowired
    private ProviderPluginLoader providerPluginLoader;

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    @EventListener(SpringApplicationRunListener.class)
    public void onStart(SpringApplicationRunListener runListener) {
        logger.info("Starting Spring Boot application");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                providerPluginLoader.stopAll();
            }

        });
    }
}
