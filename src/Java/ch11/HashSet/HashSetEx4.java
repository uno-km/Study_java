package Java.ch11.HashSet;

import java.util.HashSet;
import java.util.Objects;

public class HashSetEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet set = new HashSet();

		set.add(new String("Dabc"));
		set.add(new String("abc"));
		set.add(new String("Kimeunho"));
		set.add(new Person2("David", 10));
		set.add(new Person2("David", 10));
		set.add(new Person2("Kimeunho", 2));

		System.out.println(set);
	}
}

class Person2 {
	String name;
	int age;

	Person2(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Person2) {
			Person2 tmp = (Person2) obj;
			return name.equals(tmp.name) && age == tmp.age;
		}
		return false;
	}

	public int hashCode() {
//		return (name + age).hashCode();
		return Objects.hash(name, age);
	}

	public String toString() {
		return name + ":" + age;
	}
}