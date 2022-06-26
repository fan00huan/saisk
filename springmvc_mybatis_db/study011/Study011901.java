package jp.co.yahoo.study011;

public class Study011901 {

	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		int a=1;
		int b=2;
		int c = sum(a,b);

		System.out.println(a);
		System.out.println(c);
	}

	// 1,3
	// 2,3
	// 1,4
	// 2,4

	private static int sum(int a, int b) {
		a=2;
		return a+b;
	}


}
