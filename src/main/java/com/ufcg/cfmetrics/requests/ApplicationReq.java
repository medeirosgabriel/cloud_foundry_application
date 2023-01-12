package com.ufcg.cfmetrics.requests;

import java.util.List;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.applicationusageevents.ApplicationUsageEventResource;
import org.cloudfoundry.client.v2.applicationusageevents.GetApplicationUsageEventRequest;
import org.cloudfoundry.client.v2.applicationusageevents.GetApplicationUsageEventResponse;
import org.cloudfoundry.client.v2.applicationusageevents.ListApplicationUsageEventsRequest;
import org.cloudfoundry.client.v2.applicationusageevents.ListApplicationUsageEventsResponse;
import org.cloudfoundry.client.v3.applications.ApplicationResource;
import org.cloudfoundry.client.v3.applications.GetApplicationEnvironmentRequest;
import org.cloudfoundry.client.v3.applications.GetApplicationEnvironmentResponse;
import org.cloudfoundry.client.v3.applications.GetApplicationProcessRequest;
import org.cloudfoundry.client.v3.applications.GetApplicationProcessResponse;
import org.cloudfoundry.client.v3.applications.GetApplicationRequest;
import org.cloudfoundry.client.v3.applications.GetApplicationResponse;
import org.cloudfoundry.client.v3.applications.ListApplicationsRequest;
import org.cloudfoundry.client.v3.applications.ListApplicationsResponse;
import org.cloudfoundry.client.v3.applications.StartApplicationRequest;
import org.cloudfoundry.client.v3.applications.StartApplicationResponse;
import org.cloudfoundry.client.v3.applications.StopApplicationRequest;
import org.cloudfoundry.client.v3.applications.StopApplicationResponse;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.applications.RestartApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReq {
	
	@Autowired
	private CloudFoundryClient cloudFoundryClient;
	@Autowired
	private CloudFoundryOperations cloudFoundryOperations;
	
	public GetApplicationProcessResponse getApplicationProcessInfo(String applicationId) {
		return cloudFoundryClient
				.applicationsV3()
				.getProcess(GetApplicationProcessRequest.builder()
						.applicationId(applicationId)
						.type("web")
						.build())
				.map(response -> GetApplicationProcessResponse.builder().from(response).build())
				.block();
	}

	public GetApplicationResponse getApplication(String applicationId) {
		return cloudFoundryClient
				.applicationsV3()
				.get(GetApplicationRequest.builder()
						.applicationId(applicationId)
						.build())
				.map(response -> GetApplicationResponse.builder().from(response).build())
				.block();
	}

	public GetApplicationEnvironmentResponse getApplicationEnvironmentVariabels(String applicationId) {
		return cloudFoundryClient
				.applicationsV3()
				.getEnvironment(GetApplicationEnvironmentRequest
						.builder()
						.applicationId(applicationId)
						.build())
				.map(response -> GetApplicationEnvironmentResponse.builder().from(response).build())
				.block();
	}

	public List<ApplicationResource> getAllApplications() {
		return cloudFoundryClient
				.applicationsV3()
				.list(ListApplicationsRequest
						.builder()
						.page(1)
						.build())
				.flatMapIterable(ListApplicationsResponse::getResources)
				.map(resource -> ApplicationResource.builder().from(resource).build())
				.collectList().block();
	}
	
	public StartApplicationResponse startApplication(String applicationId) {
		return cloudFoundryClient.applicationsV3()
				.start(StartApplicationRequest.builder()
						.applicationId(applicationId)
						.build())
				.map(resource -> StartApplicationResponse.builder().from(resource).build())
				.block();
	}
	
	public  StopApplicationResponse stopApplication(String applicationId) {
		return cloudFoundryClient.applicationsV3()
				.stop(StopApplicationRequest.builder()
						.applicationId(applicationId)
						.build())
				.map(resource -> StopApplicationResponse.builder().from(resource).build())
				.block();
	}
}
