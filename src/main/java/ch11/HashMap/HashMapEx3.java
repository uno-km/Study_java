package ch11.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapEx3 {
	private static final String HashMap = null;
	static HashMap phoneBook = new HashMap();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addPhoneNo("Friends", "unoKim", "010-4943-7334");
		addPhoneNo("Friends", "yahoChun", "010-9119-9837");
		addPhoneNo("Friends", "dwaniKim", "010-6438-8125");
		addPhoneNo("Friends", "hunaHwang", "010-2059-2976");
		addPhoneNo("Friends", "parkJi", "010-4229-4128");
		printList();
	}

	static void addPhoneNo(String groupname, String name, String tel) {
		addGroup(groupname);
		HashMap group = (HashMap) phoneBook.get(groupname);
		group.put(tel, name);

	}

	static void addGroup(String groupname) {
		if (!phoneBook.containsKey(groupname)) {
			phoneBook.put(groupname, new HashMap());
		}
	}

	static void addPhoneNo(String name, String tel) {
		addPhoneNo("other", name, tel);
	}

	static void printList() {
		Set set = phoneBook.entrySet();

		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();

			Set subSet = ((HashMap) e.getValue()).entrySet();
			Iterator subIt = subSet.iterator();

			Map.Entry subE = (Map.Entry) subIt.next();
			String telNo = (String) subE.getKey();
			String name = (String) subE.getValue();

			System.out.println(name + " " + telNo);
		}
		System.out.println();
	}

}
