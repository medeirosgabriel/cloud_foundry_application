package com.ufcg.cfmetrics.dto;

public class PromRequestDTO {
	
	private String query;
	private String start;
	private String end;
	private String step;
	
	public PromRequestDTO(String query, String start, String end, String step) {
		super();
		this.query = query;
		this.start = start;
		this.end = end;
		this.step = step;
	}

	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getStep() {
		return step;
	}
	
	public void setStep(String step) {
		this.step = step;
	}
}
