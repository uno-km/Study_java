package hycy.study3;

public class test1 {
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
//		boolean a =  new Boolean(true);
//		boolean b =  new Boolean(false);
//		(!a&&b)||(a&&!b);
		System.out.println((a.equals(b))^(!b.equals(b)));
	}
}
