package com.ufcg.cfmetrics.models;

public class AllProcessesStatistic {
	
	private double cpuAvg;
	private double memAvg;
	private double diskAvg;
	private double cpuStd;
	private double memStd;
	private double diskStd;
	
	public AllProcessesStatistic(double cpuAvg, double memAvg, double diskAvg, double cpuStd, double memStd,
			double diskStd) {
		super();
		this.cpuAvg = cpuAvg;
		this.memAvg = memAvg;
		this.diskAvg = diskAvg;
		this.cpuStd = cpuStd;
		this.memStd = memStd;
		this.diskStd = diskStd;
	}

	public double getCpuAvg() {
		return cpuAvg;
	}
	
	public void setCpuAvg(double cpuAvg) {
		this.cpuAvg = cpuAvg;
	}
	
	public double getMemAvg() {
		return memAvg;
	}
	
	public void setMemAvg(double memAvg) {
		this.memAvg = memAvg;
	}
	
	public double getDiskAvg() {
		return diskAvg;
	}
	
	public void setDiskAvg(double diskAvg) {
		this.diskAvg = diskAvg;
	}
	
	public double getCpuStd() {
		return cpuStd;
	}
	
	public void setCpuStd(double cpuStd) {
		this.cpuStd = cpuStd;
	}
	
	public double getMemStd() {
		return memStd;
	}
	
	public void setMemStd(double memStd) {
		this.memStd = memStd;
	}
	
	public double getDiskStd() {
		return diskStd;
	}
	
	public void setDiskStd(double diskStd) {
		this.diskStd = diskStd;
	}
}
