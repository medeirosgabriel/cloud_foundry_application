package com.ufcg.cfmetrics.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.cfmetrics.dto.PromRequestDTO;
import com.ufcg.cfmetrics.requests.MetricReq;

@Service
public class MetricService {
	
	@Autowired
	private MetricReq metricReq;
	
	public String getPrometheusMetrics(PromRequestDTO promRequest) throws IOException {
		return this.metricReq.getPrometheusMetrics(promRequest.getQuery(), promRequest.getStart(), promRequest.getEnd(), promRequest.getStep());
	}

}
