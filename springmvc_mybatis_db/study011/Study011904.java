package jp.co.yahoo.study011;

public class Study011904 {

	public static void main(String[] args) {

		int[] arr = {1,2,3};

		arr[0] = 9;
		//System.out.println(arr[1]);

//		for(int i=0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}

		for (int num : arr) {
			System.out.println(num);
		}

	}

}
