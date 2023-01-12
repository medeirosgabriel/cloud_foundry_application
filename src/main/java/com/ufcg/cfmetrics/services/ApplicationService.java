package com.ufcg.cfmetrics.services;

import java.util.List;

import org.cloudfoundry.client.v2.applicationusageevents.ApplicationUsageEventResource;
import org.cloudfoundry.client.v2.applicationusageevents.GetApplicationUsageEventResponse;
import org.cloudfoundry.client.v3.applications.ApplicationResource;
import org.cloudfoundry.client.v3.applications.GetApplicationEnvironmentResponse;
import org.cloudfoundry.client.v3.applications.GetApplicationProcessResponse;
import org.cloudfoundry.client.v3.applications.GetApplicationResponse;
import org.cloudfoundry.client.v3.applications.StartApplicationResponse;
import org.cloudfoundry.client.v3.applications.StopApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.cfmetrics.requests.ApplicationReq;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationReq appReq;
	
	public GetApplicationProcessResponse getApplicationProcessInfo(String applicationId) {
		return this.appReq.getApplicationProcessInfo(applicationId);
	}

	public GetApplicationResponse getApplication(String applicationId) {
		return this.appReq.getApplication(applicationId);
	}

	public GetApplicationEnvironmentResponse getApplicationEnvironmentVariabels(String applicationId) {
		return this.appReq.getApplicationEnvironmentVariabels(applicationId);
	}

	public List<ApplicationResource> getAllApplications() {
		return this.appReq.getAllApplications();
	}
	
	public StartApplicationResponse startApplication(String applicationId) {
		return this.appReq.startApplication(applicationId);
	}

	public  StopApplicationResponse stopApplication(String applicationId) {
		return this.appReq.stopApplication(applicationId);
	}
}