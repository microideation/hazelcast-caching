package com.microideation.samples.hazelcastcaching.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Value("${cache.hostnames}")
    private String hosts;

    @Value("${cache.username}")
    private String username;

    @Value("${cache.password}")
    private String password;

    /**
     * Method to create the hazelcastCacheManager using the settings
     * @return : Return the HazelcastCacheManager with the details
     */
    @Bean
    public HazelcastCacheManager hazelcastCacheManager() {

        // Create the cacheConfig
        ClientConfig config = new ClientConfig();

        // Set the username
        config.getGroupConfig().setName(username);

        // Set the password
        config.getGroupConfig().setPassword(password);

        // Split the address by #
        String addresses[] = hosts.split("#");

        // Set the addresses
        config.getNetworkConfig().addAddress(addresses);

        // Create the hazelcast instance
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(config);

        // Return the hazelcastManager with the created instance
        return new HazelcastCacheManager(instance);

    }

}
