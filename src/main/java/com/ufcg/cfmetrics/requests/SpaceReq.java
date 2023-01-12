package com.ufcg.cfmetrics.requests;

import java.util.List;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.spacequotadefinitions.GetSpaceQuotaDefinitionRequest;
import org.cloudfoundry.client.v2.spacequotadefinitions.GetSpaceQuotaDefinitionResponse;
import org.cloudfoundry.client.v2.spacequotadefinitions.ListSpaceQuotaDefinitionSpacesResponse;
import org.cloudfoundry.client.v2.spacequotadefinitions.ListSpaceQuotaDefinitionsRequest;
import org.cloudfoundry.client.v2.spacequotadefinitions.ListSpaceQuotaDefinitionsResponse;
import org.cloudfoundry.client.v2.spacequotadefinitions.SpaceQuotaDefinitionResource;
import org.cloudfoundry.client.v3.spaces.GetSpaceRequest;
import org.cloudfoundry.client.v3.spaces.ListSpacesRequest;
import org.cloudfoundry.client.v3.spaces.ListSpacesResponse;
import org.cloudfoundry.client.v3.spaces.SpaceResource;
import org.cloudfoundry.client.v3.spaces.GetSpaceResponse;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceReq {
	
	@Autowired
	private CloudFoundryClient cloudFoundryClient;
	@Autowired
	private CloudFoundryOperations cloudFoundryOperations;
	
	public List<SpaceResource> getSpaces() {
		return this.cloudFoundryClient.spacesV3()
				.list(ListSpacesRequest.builder().build())
				.flatMapIterable(ListSpacesResponse::getResources)
		        .map(resource -> SpaceResource.builder().from(resource).build())
		        .collectList().block();
	}
	
	public GetSpaceResponse getSpaceById(String spaceId) {
		
		return this.cloudFoundryClient.spacesV3()
				.get(GetSpaceRequest.builder().spaceId(spaceId).build())
		        .map(resource -> GetSpaceResponse.builder().from(resource).build())
		        .block();
		
	}
	
	public List<SpaceQuotaDefinitionResource> getSpacesQuota() {
		return this.cloudFoundryClient.spaceQuotaDefinitions()
				.list(ListSpaceQuotaDefinitionsRequest.builder().build())
				.flatMapIterable(ListSpaceQuotaDefinitionsResponse::getResources)
				.map(resource -> SpaceQuotaDefinitionResource.builder().from(resource).build())
		        .collectList().block();
	}
	
	public GetSpaceQuotaDefinitionResponse getSpaceQuotaById(String spaceId) {
		return this.cloudFoundryClient.spaceQuotaDefinitions()
				.get(GetSpaceQuotaDefinitionRequest.builder().spaceQuotaDefinitionId(spaceId).build())
				.map(resource -> GetSpaceQuotaDefinitionResponse.builder().from(resource).build())
		        .block();
	}
}
