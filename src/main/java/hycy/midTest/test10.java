package hycy.midTest;

public class test10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Insa Insa = new Insa();
		Insa.bye();
	}

}

class Greeting {
	void bye() {
		System.out.println("good bye");
	}
}

class Insa extends Greeting {
	void bye() {
		super.bye();
	}
}
