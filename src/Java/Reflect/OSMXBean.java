package Java.Reflect;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author zhfld
 * @version 1.0.1
 * @since 2022.11.18
 * @category OperatingSystemMXBean을 사용못한 한이 맺힌 이넘클래스
 * @implNote 리턴타입 Object 인 getValue()를 상속시킨다.
 * @apiNote
 *          <p>
 *          기존의 OperatingSystemMXBean를 호출하고 이것저것해서 바로 get하면서 갑자기 사용해버리는 그런 포스팅을
 *          봤지만 나는 그것을 사용하지 못해 3일간 개뻘짓 한것이 열불나서 그냥 내가 확만들어버린 라이브러리 그러나 효율성이나 성능은
 *          보장못함
 */
enum OSMXBean implements MyOperatingSystemMXBean
{
	COMMITTED_VIRTUAL_MEMORY_SIZE
	{
		@Override
		public Object getValue()
		{
			return this.excute(getCommittedVirtualMemorySize);
		}
	},
	FREE_PHYSICAL_MEMORY_SIZE
	{
		@Override
		public Object getValue()
		{
			return this.excute(getFreePhysicalMemorySize);
		}
	},
	FREE_SWAP_SPACE_SIZE
	{
		@Override
		public Object getValue()
		{
			return this.excute(getFreeSwapSpaceSize);
		}
	},
	PROCESS_CPU_LOAD
	{
		@Override
		public Object getValue()
		{
			return this.excute(getProcessCpuLoad);
		}
	},
	PROCESS_CPU_TIME
	{
		@Override
		public Object getValue()
		{
			return this.excute(getProcessCpuTime);
		}
	},
	SYSTEM_CPU_LOAD
	{
		@Override
		public Object getValue()
		{
			return this.excute(getSystemCpuLoad);
		}
	},
	TOTAL_PHYSICAL_MEMORY_SIZE
	{
		@Override
		public Object getValue()
		{
			return this.excute(getTotalPhysicalMemorySize);
		}
	},
	TOTAL_SWAP_SPACE_SIZE
	{
		@Override
		public Object getValue()
		{
			return this.excute(getTotalSwapSpaceSize);
		}
	};
	private HashMap<String, Method> methodMap = new HashMap<String, Method>();
	private OperatingSystemMXBean osBean;
	
	OSMXBean()
	{
		this.osBean = ManagementFactory.getOperatingSystemMXBean();
		Method[] methods = osBean.getClass().getDeclaredMethods();
		for (Method method : methods)
		{
			method.setAccessible(true);
			this.methodMap.put(method.getName(), method);
		}
	}
	protected Object excute(String str)
	{
		Object value = null;
		try
		{
			value = this.methodMap.get(str).invoke(this.osBean);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return value;
	}
}