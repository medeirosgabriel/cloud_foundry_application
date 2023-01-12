package com.ufcg.cfmetrics.dto;

public class ProcessStatsDTO {
	
	private String type;
	
	public ProcessStatsDTO () {}

	public ProcessStatsDTO(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

