package jp.co.yahoo.study011;

public class Study011902 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		int a=1;
		//System.out.println(a++);
		//System.out.println(++a);

		//funIfElse();
		//funFor();
		//funWhile();
		//funDoWhile();
		//fun3();
		//funSwitch();
	}

	private static void fun3() {
		System.out.println(1 == 2 ? "yes" : "no");
	}

	private static void funSwitch() {
		int a = 1;
		char ch;

		switch (a) {
		case 1:
			System.out.println("1");
			break;
		case 2:
			System.out.println("2");
			break;
		default:
			break;
		}
	}

	private static void funIfElse() {
		int sum1 = 0;
		int sum0 = 0;
		for(int i = 1; i <= 100; i++) {
			if (i%2==0) {
				sum0 = sum0+i;
			} else {
				sum1 = sum1+i;
			}
		}
		System.out.println(sum0);
		System.out.println(sum1);
	}

	private static void funDoWhile() {
		int sum = 0;
		int i=1;

		do {
			sum = sum + i;
			i++;
		}
		while(i<=100);

		System.out.println(sum);
	}

	private static void funWhile() {
		int sum = 0;
		int i=1;
		while(i<=100) {
			sum = sum + i;
			i++;
		}
		System.out.println(sum);
	}

	private static void funFor() {
		int sum = 0;
		for(int i = 1; i <= 100; i++) {
			sum = sum + i;
		}
		System.out.println(sum);
	}

}
