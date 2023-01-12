package com.ufcg.cfmetrics.requests;

import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v3.applications.GetApplicationProcessStatisticsRequest;
import org.cloudfoundry.client.v3.applications.GetApplicationProcessStatisticsResponse;
import org.cloudfoundry.client.v3.applications.TerminateApplicationInstanceRequest;
import org.cloudfoundry.client.v3.processes.GetProcessRequest;
import org.cloudfoundry.client.v3.processes.GetProcessResponse;
import org.cloudfoundry.client.v3.processes.ListProcessesRequest;
import org.cloudfoundry.client.v3.processes.ListProcessesResponse;
import org.cloudfoundry.client.v3.processes.ProcessResource;
import org.cloudfoundry.client.v3.processes.ProcessStatisticsResource;
import org.cloudfoundry.client.v3.processes.ScaleProcessRequest;
import org.cloudfoundry.client.v3.processes.ScaleProcessResponse;
import org.cloudfoundry.client.v3.processes.TerminateProcessInstanceRequest;
import org.cloudfoundry.operations.CloudFoundryOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ufcg.cfmetrics.dto.ProcessStatsDTO;
import com.ufcg.cfmetrics.dto.ScaleProcessDTO;
import com.ufcg.cfmetrics.dto.TerminateProcessDTO;
import com.ufcg.cfmetrics.models.AllProcessesStatistic;

@Component
public class ProcessReq {
	
	@Autowired
	private CloudFoundryClient cloudFoundryClient;
	@Autowired
	private CloudFoundryOperations cloudFoundryOperations;
	
	public ScaleProcessResponse scaleProcess(String processId, ScaleProcessDTO scaleProcessDTO) {
		return cloudFoundryClient
				.processes().scale(ScaleProcessRequest.builder()
						.processId(processId)
						.diskInMb(scaleProcessDTO.getDiskInMb())
						.instances(scaleProcessDTO.getInstances())
						.memoryInMb(scaleProcessDTO.getMemoryInMb())
						.build())
				.map(resource -> ScaleProcessResponse.builder().from(resource).build())
		        .block();
	}
	
	public GetProcessResponse getProcessById(String processId) {
		return cloudFoundryClient
				.processes().get(GetProcessRequest.builder().processId(processId).build())
				.map(resource -> GetProcessResponse.builder().from(resource).build())
		        .block();
	}
	
	public List<ProcessResource> getAllProcesses(String applicationId) {
		return (ArrayList<ProcessResource>) cloudFoundryClient
				.processes().list(ListProcessesRequest.builder().applicationId(applicationId).page(1).build())
				.flatMapIterable(ListProcessesResponse::getResources)
		        .map(resource -> ProcessResource.builder().from(resource).build())
		        .collectList().block();
				
	}

	public List<ProcessStatisticsResource> getProcessesStatisticsByApp(String applicationId, ProcessStatsDTO processStatsDTO) {
		return (ArrayList<ProcessStatisticsResource>) cloudFoundryClient
				.applicationsV3()
				.getProcessStatistics(GetApplicationProcessStatisticsRequest.builder()
						.applicationId(applicationId)
						.type(processStatsDTO.getType())
						.build())
				.flatMapIterable(GetApplicationProcessStatisticsResponse::getResources)
		        .map(resource -> ProcessStatisticsResource.builder().from(resource).build())
		        .collectList().block();
	}
	
	public AllProcessesStatistic getAllProcessesStatistics(String applicationId, ProcessStatsDTO processStatsDTO) {
		List<ProcessStatisticsResource> allProcesses = (ArrayList<ProcessStatisticsResource>) cloudFoundryClient
			.applicationsV3()
			.getProcessStatistics(GetApplicationProcessStatisticsRequest.builder()
					.applicationId(applicationId)
					.type(processStatsDTO.getType())
					.build())
			.flatMapIterable(GetApplicationProcessStatisticsResponse::getResources)
	        .map(resource -> ProcessStatisticsResource.builder().from(resource).build())
	        .collectList().block();
		
		return this.getAllProcessStatistic(allProcesses);
	}
	
	private AllProcessesStatistic getAllProcessStatistic(List<ProcessStatisticsResource> allProcesses) {
		double cpuAvg = 0;
		double diskAvg = 0;
		double memAvg = 0;
		for (ProcessStatisticsResource p : allProcesses) {
			cpuAvg += p.getUsage().getCpu();
			diskAvg += p.getUsage().getDisk();
			memAvg += p.getUsage().getMemory();
		}
		cpuAvg /= allProcesses.size();
		diskAvg /= allProcesses.size();
		memAvg /= allProcesses.size();
		
		double cpuStd = 0;
		double diskStd = 0;
		double memStd = 0;
		for (ProcessStatisticsResource p : allProcesses) {
			cpuStd += Math.pow(p.getUsage().getCpu() - cpuAvg, 2);
			diskStd += Math.pow(p.getUsage().getDisk() - diskAvg, 2);
			memStd += Math.pow(p.getUsage().getMemory() - memAvg, 2);
		}
		cpuStd /= allProcesses.size();
		cpuStd = Math.sqrt(cpuStd);
		diskStd /= allProcesses.size();
		diskStd = Math.sqrt(diskAvg);
		memStd /= allProcesses.size();
		memStd = Math.sqrt(memAvg);
		
		return new AllProcessesStatistic(cpuAvg, memAvg, diskAvg, cpuStd, memStd, diskStd);
	}

	public void terminateProcess(String processId, TerminateProcessDTO terminateProcessDTO) {
		
		cloudFoundryClient
			.processes()
			.terminateInstance(TerminateProcessInstanceRequest.builder()
					.processId(processId)
					.index(terminateProcessDTO.getIndex())
					.build())
			.block();
	}
}
