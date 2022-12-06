package Java.Reflect;

interface MyOperatingSystemMXBean
{
	abstract Object getValue();
	
	public final String getCommittedVirtualMemorySize = "getCommittedVirtualMemorySize";
	public final String getFreePhysicalMemorySize = "getFreePhysicalMemorySize";
	public final String getFreeSwapSpaceSize = "getFreeSwapSpaceSize";
	public final String getProcessCpuLoad = "getProcessCpuLoad";
	public final String getProcessCpuTime = "getProcessCpuTime";
	public final String getSystemCpuLoad = "getSystemCpuLoad";
	public final String getTotalPhysicalMemorySize = "getTotalPhysicalMemorySize";
	public final String getTotalSwapSpaceSize = "getTotalSwapSpaceSize";
}