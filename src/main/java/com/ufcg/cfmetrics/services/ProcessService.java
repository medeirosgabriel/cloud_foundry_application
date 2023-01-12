package com.ufcg.cfmetrics.services;

import java.util.List;

import org.cloudfoundry.client.v3.processes.GetProcessResponse;
import org.cloudfoundry.client.v3.processes.ProcessResource;
import org.cloudfoundry.client.v3.processes.ProcessStatisticsResource;
import org.cloudfoundry.client.v3.processes.ScaleProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.cfmetrics.dto.ProcessStatsDTO;
import com.ufcg.cfmetrics.dto.ScaleProcessDTO;
import com.ufcg.cfmetrics.dto.TerminateProcessDTO;
import com.ufcg.cfmetrics.models.AllProcessesStatistic;
import com.ufcg.cfmetrics.requests.ProcessReq;

@Service
public class ProcessService {
	
	@Autowired
	private ProcessReq processReq;
	
	public ScaleProcessResponse scaleProcess(String processId, ScaleProcessDTO scaleProcessDTO) {
		return this.processReq.scaleProcess(processId, scaleProcessDTO);
	}
	
	public List<ProcessResource> getAllProcesses(String applicationId) {
		return this.processReq.getAllProcesses(applicationId);
	}

	public List<ProcessStatisticsResource> getProcessesStatisticsByApp(String applicationId, ProcessStatsDTO processStatsDTO) {
		return this.processReq.getProcessesStatisticsByApp(applicationId, processStatsDTO);
	}
	
	public AllProcessesStatistic getAllProcessesStatistic(String applicationId, ProcessStatsDTO processStatsDTO) {
		return this.processReq.getAllProcessesStatistics(applicationId, processStatsDTO);
	}

	public GetProcessResponse getProcessById(String processId) {
		return this.processReq.getProcessById(processId);
	}

	public void terminateProcess(String processId, TerminateProcessDTO terminateProcessDTO) {
		this.processReq.terminateProcess(processId, terminateProcessDTO);
	}
}
