package Java.DataBase.Memory;

import java.util.Map;

public abstract class SQLCache {
	private Map<String, Object> cache;

	public Map<String, Object> getCache() {
		return cache;
	}

	protected void setCache(Map<String, Object> cache) {
		this.cache = cache;
	}
}
