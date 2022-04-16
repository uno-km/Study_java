package hycy.midTest;

public class test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calc ob = new Calc();
		System.out.println("b-a =" + ob.sub(3, 9));
	}

}

class Calc {
	int sub(int a, int b) {
		return b - a;
	}
}
