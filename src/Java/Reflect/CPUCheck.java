package Java.Reflect;

public class CPUCheck
{
	public static void main(String[] args)
	{
		System.out.println(OSMXBean.COMMITTED_VIRTUAL_MEMORY_SIZE.getValue());
		System.out.println(OSMXBean.FREE_PHYSICAL_MEMORY_SIZE.getValue());
		System.out.println(OSMXBean.FREE_SWAP_SPACE_SIZE.getValue());
		System.out.println(OSMXBean.PROCESS_CPU_LOAD.getValue());
		System.out.println(OSMXBean.PROCESS_CPU_TIME.getValue());
		System.out.println(OSMXBean.SYSTEM_CPU_LOAD.getValue());
		System.out.println(OSMXBean.TOTAL_PHYSICAL_MEMORY_SIZE.getValue());
		System.out.println(OSMXBean.TOTAL_SWAP_SPACE_SIZE.getValue());
	}
}
