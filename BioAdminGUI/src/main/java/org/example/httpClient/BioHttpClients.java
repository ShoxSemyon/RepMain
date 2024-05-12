package org.example.httpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class BioHttpClients {

    @Bean
    public MemberClient memberClient() {

        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter
                        .create(RestClient.builder()
                                .requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                                .build()))
                .build()
                .createClient(MemberClient.class);
    }
}