package hycy.midTest;

class Nation {
	final int x = 50;
}

class Country extends Nation {
}

class CTest {
	public static void main(String[] args) {
		Nation us = new Nation();
		Country ab = new Country();
	}

}