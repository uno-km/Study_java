package Java.Reflect;

interface MyOperatingSystemMXBean
{
	abstract Object getValue();
	String[] METHODS = {
			"getCommittedVirtualMemorySize"
			,"getFreePhysicalMemorySize"
			,"getFreeSwapSpaceSize"
			,"getProcessCpuLoad"
			,"getProcessCpuTime"
			,"getSystemCpuLoad"
			,"getTotalPhysicalMemorySize"
			,"getTotalSwapSpaceSize"
	};
}
