package com.ufcg.cfmetrics.config;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.doppler.DopplerClient;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;
import org.cloudfoundry.uaa.UaaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class CFConfig {
	
	@Bean
	ConnectionContext connectionContext(@Value("${cf.apiHost}") String apiHost) {
	    return (ConnectionContext) DefaultConnectionContext.builder()
	        .apiHost(apiHost)
	        .build();
	}

	@Bean
	PasswordGrantTokenProvider tokenProvider(@Value("${cf.username}") String username,
	                                         @Value("${cf.password}") String password,
	                                         @Value("${cf.clientId}") String clientId,
	                                         @Value("${cf.clientSecret}") String clientSecret) {
		
	    return PasswordGrantTokenProvider.builder()
	    		.password(password)
		        .username(username)
		        .build();
	}
	
	@Bean
	UaaClient uaaClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
	    return ReactorUaaClient.builder()
	        .connectionContext(connectionContext)
	        .tokenProvider(tokenProvider)
	        .build();
	}
	
	@Bean
	DopplerClient dopplerClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
	    return ReactorDopplerClient.builder()
	        .connectionContext(connectionContext)
	        .tokenProvider(tokenProvider)
	        .build();
	}
	
	@Bean
	CloudFoundryClient cloudFoundryClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
	    return ReactorCloudFoundryClient.builder()
	        .connectionContext(connectionContext)
	        .tokenProvider(tokenProvider)
	        .build();
	}
	
	@Bean
	CloudFoundryOperations cloudFoundryOperations(CloudFoundryClient cloudFoundryClient,
														 DopplerClient dopplerClient,
														 UaaClient uaaClient,
	                                                     @Value("${cf.organization}") String organization,
	                                                     @Value("${cf.space}") String space) {
	    return DefaultCloudFoundryOperations.builder()
	            .cloudFoundryClient(cloudFoundryClient)
	            .dopplerClient(dopplerClient)
	            .uaaClient(uaaClient)
	            .organization(organization)
	            .space(space)
	            .build();
	}
}
