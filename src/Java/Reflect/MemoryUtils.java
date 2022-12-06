package Java.Reflect;

public class MemoryUtils
{
	/**
	 * @apiNote getCommittedVirtualMemorySize return Long type
	 * @apiNote 배정된 가상 메모리 사이즈 반환(long 타입)
	 */
	static public long getCommittedVirtualMemotySize()
	{
		return (long) OSMXBean.COMMITTED_VIRTUAL_MEMORY_SIZE.getValue();
	}
	/**
	 * @apiNote getFreePhysicalMemorySize return Long type
	 * @apiNote 사용가능한 물리적 메모리 사이즈 반환(long 타입)
	 */
	static public long getFreePhysicalMemorySize()
	{
		return (long) OSMXBean.FREE_PHYSICAL_MEMORY_SIZE.getValue();
	}
	/**
	 * @apiNote getFreeSwapSpaceSize return Long type
	 * @apiNote 스왑가능한 공간 사이즈 반환(long 타입)
	 */
	static public long getFreeSwapSpaceSize()
	{
		return (long) OSMXBean.FREE_SWAP_SPACE_SIZE.getValue();
	}
	/**
	 * @apiNote getProcessCpuLoad return Double type
	 * @apiNote 최근 CPU 사용량을 반환 (double)
	 */
	static public double getProcessCpuLoad()
	{
		return (double) OSMXBean.PROCESS_CPU_LOAD.getValue();
	}
	/**
	 * @apiNote getProcessCpuTime return Long type
	 * @apiNote 자바가 사용되는 프로세스에서 사용된 CPU 시간을 반환합니다.
	 */
	static public long getProcessCpuTime()
	{
		return (long) OSMXBean.PROCESS_CPU_TIME.getValue();
	}
	/**
	 * @apiNote getSystemCpuLoad return Double type
	 * @apiNote 전체 시스템의 최근 CPU 사용량을 반환 (double)
	 */
	static public double getSystemCpuLoad()
	{
		return (double) OSMXBean.SYSTEM_CPU_LOAD.getValue();
	}
	/**
	 * @apiNote getTotalPhysicalMemorySize return Long type
	 * @apiNote 물리메모리의 합계량을 반환 (long)
	 */
	static public long getTotalPhysicalMemorySize()
	{
		return (long) OSMXBean.TOTAL_PHYSICAL_MEMORY_SIZE.getValue();
	}
	/**
	 * @apiNote getTotalSwapSpaceSize return Long type
	 * @apiNote 총 스왑 공간을 반환 (long)
	 */
	static public long getTotalSwapSpaceSize()
	{
		return (long) OSMXBean.TOTAL_SWAP_SPACE_SIZE.getValue();
	}
}