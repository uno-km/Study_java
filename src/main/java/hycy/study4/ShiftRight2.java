package hycy.study4;

public class ShiftRight2 {
	public static void main(String[] args) {
		byte a = -100, b, N = 2;
		int bitmask = 0x0000000FF;
		int ta, tb;
		b = (byte) ((int) a >> N);
		System.out.printf("10진수 : %d >> %d = %d \n", a, N, b);
		System.out.printf("16진수 : 0x$X >> %d = 0 x%X \n", a, N, b);
		System.out.println("2진수 : " + Integer.toBinaryString(a) + ">>" + N);
		System.out.println(" = " + Integer.toBinaryString(b));

		ta = a & bitmask;
		tb = b & bitmask;
		System.out.println("하위 1바이트 2진수 " + Integer.toBinaryString(ta) + ">>" + N);
		System.out.println(Integer.toBinaryString(tb));

	}
}
