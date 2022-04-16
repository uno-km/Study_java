package hycy.midTest;

public class test4 {
	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.printMsg();
	}
}

class Super {
	void printMsg() {
		System.out.println("상위 클래스 입니다.");
	}
}

class Sub extends Super {
	void printMsg() {
		super.printMsg();
		System.out.println(" 서브 클래스 입니다.");
	}
}