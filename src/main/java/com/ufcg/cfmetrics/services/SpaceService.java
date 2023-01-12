package com.ufcg.cfmetrics.services;

import java.util.List;

import org.cloudfoundry.client.v3.spaces.SpaceResource;
import org.cloudfoundry.client.v2.spacequotadefinitions.GetSpaceQuotaDefinitionResponse;
import org.cloudfoundry.client.v2.spacequotadefinitions.SpaceQuotaDefinitionResource;
import org.cloudfoundry.client.v3.spaces.GetSpaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.cfmetrics.requests.SpaceReq;

@Service
public class SpaceService {
	
	@Autowired
	private SpaceReq spaceReq;

	public List<SpaceResource> getSpaces() {
		return this.spaceReq.getSpaces();
	}
	
	public GetSpaceResponse getSpaceById(String spaceId) {
		return this.spaceReq.getSpaceById(spaceId);
	}
	
	public List<SpaceQuotaDefinitionResource> getSpacesQuota() {
		return this.spaceReq.getSpacesQuota();
	}
	
	public GetSpaceQuotaDefinitionResponse getSpaceQuotaById(String spaceId) {
		return this.spaceReq.getSpaceQuotaById(spaceId);
	}
}
