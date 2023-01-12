package com.ufcg.cfmetrics.dto;

public class TerminateProcessDTO {
	
	private String index;
	private String type;
	
	public TerminateProcessDTO(String index, String type) {
		super();
		this.index = index;
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
