package com.ufcg.cfmetrics.dto;

public class ScaleProcessDTO {
	
	private Integer diskInMb;
	private Integer instances; 
	private Integer memoryInMb;
	
	public ScaleProcessDTO(Integer diskInMb, Integer instances, Integer memoryInMb) {
		super();
		this.diskInMb = diskInMb;
		this.instances = instances;
		this.memoryInMb = memoryInMb;
	}

	public Integer getDiskInMb() {
		return diskInMb;
	}
	
	public void setDiskInMb(Integer diskInMb) {
		this.diskInMb = diskInMb;
	}
	
	public Integer getInstances() {
		return instances;
	}
	
	public void setInstances(Integer instances) {
		this.instances = instances;
	}
	
	public Integer getMemoryInMb() {
		return memoryInMb;
	}
	
	public void setMemoryInMb(Integer memoryInMb) {
		this.memoryInMb = memoryInMb;
	}
}
