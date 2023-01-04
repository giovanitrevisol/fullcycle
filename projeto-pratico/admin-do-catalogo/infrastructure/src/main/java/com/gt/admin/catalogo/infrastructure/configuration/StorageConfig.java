package com.gt.admin.catalogo.infrastructure.configuration;

import com.google.cloud.storage.Storage;
import com.gt.admin.catalogo.infrastructure.configuration.properties.google.GoogleStorageProperties;
import com.gt.admin.catalogo.infrastructure.configuration.properties.storage.StorageProperties;
import com.gt.admin.catalogo.infrastructure.services.StorageService;
import com.gt.admin.catalogo.infrastructure.services.impl.GCStorageService;
import com.gt.admin.catalogo.infrastructure.services.local.InMemoryStorageService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StorageConfig {

    @Bean
    @ConfigurationProperties(value = "storage.catalogo-videos")
    public StorageProperties storageProperties() {
        return new StorageProperties();
    }

    @Bean
    @Profile({ "production", "development" })
    public StorageService gcStorageAPI(
            final GoogleStorageProperties props,
            final Storage storage) {
        return new GCStorageService(props.getBucket(), storage);
    }

    @Bean
    @ConditionalOnMissingBean
    public StorageService localStorageAPI() {
        return new InMemoryStorageService();
    }
}
