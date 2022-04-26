package com.klexhub.msfop.providers.plugin;

import com.klexhub.msfop.api.SMSProvider;
import org.pf4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderPluginLoader {

    private static final Logger logger = LoggerFactory.getLogger(ProviderPluginLoader.class);
    private List<SMSProvider> smsProviders;

    private final PluginManager pluginManager;

    public ProviderPluginLoader() {
        // create the plugin manager
        this.pluginManager= new DefaultPluginManager() {
            @Override
            protected CompoundPluginDescriptorFinder createPluginDescriptorFinder() {
                return new CompoundPluginDescriptorFinder()
                    // Demo is using the Manifest file
                    // PropertiesPluginDescriptorFinder is commented out just to avoid error log
                    .add(new PropertiesPluginDescriptorFinder())
                    .add(new ManifestPluginDescriptorFinder());
            }
        };
        this.loadProviderPlugins();
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public void stopAll() {
        pluginManager.stopPlugins();
    }

    private void loadProviderPlugins() {
        pluginManager.loadPlugins();
        // start (active/resolved) the plugins
        pluginManager.startPlugins();

        logger.info("Plugindirectory: ");
        logger.info("\t" + System.getProperty("pf4j.pluginsDir", "plugins") + "\n");

        // retrieves the extensions for Greeting extension point
        List<SMSProvider> smsProviders = pluginManager.getExtensions(SMSProvider.class);
        logger.info(String.format("Found %d extensions for extension point '%s'", smsProviders.size(), SMSProvider.class.getName()));
        this.smsProviders = smsProviders;
    }

    public List<SMSProvider> getSmsProviders() {
        return smsProviders;
    }
    public SMSProvider getSmsProvider() {
        return smsProviders.size() > 0 ? smsProviders.get(0) : null;
    }

}
