package Java.ch12.Enum;

class TIME {
	static final int EAST = 10;
	static final int SOUTH = 20;
	static final int WEST = 30;
	static final int NORTH = 40;

	static final Direction direction = null;
}

enum Direction {
	EAST, SOUTH, WEST, NORTH, HERE
}

public class EnumEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Direction d1 = Direction.EAST;
		Direction d2 = Direction.valueOf("WEST");
		Direction d3 = Enum.valueOf(Direction.class, "EAST");
		System.out.println("d1=" + d1);
		System.out.println("d2=" + d2);
		System.out.println("d3=" + d3);
		System.out.println(Direction.NORTH);
		System.out.println("d1==d2 ? " + (d1 == d2));
		System.out.println("d1==d3 ? " + (d1 == d3));
		System.out.println("d1.equal(d3) ? " + d1.equals(d3));
//		System.out.println("d1 > d3 ? " + (d1 > d3)); //d1,d3 객체라 연산자 불가능
		System.out.println("d1.compareTo(d3) ? " + (d1.compareTo(d3)));
		System.out.println("d1.compareTo(d2) ? " + (d1.compareTo(d2)));
		// 아래랑 같음 의미상은
//		System.out.println("d1.compareTo(d2) ? " + (EAST.compareTo(WEST)));

		switch (d3) {
		case EAST:
			System.out.println("The direction is EAST.");
			break;
		case WEST:
			System.out.println("The direction is WEST.");
			break;
		case SOUTH:
			System.out.println("The direction is SOUTH.");
			break;
		case NORTH:
			System.out.println("The direction is NORTH.");
			break;
		default:
			System.out.println("Invalid direction.");
			break;
		}
		Direction[] dArr = Direction.values();
		for (Direction d : dArr) {
			System.out.printf("%s=%d%n", d.name(), d.ordinal()); // 상수들이 가진 값하고 무관함
		}
	}
}
