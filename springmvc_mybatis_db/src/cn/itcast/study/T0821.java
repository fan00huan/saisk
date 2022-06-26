package cn.itcast.study;
import java.util.ArrayList;
import java.util.List;

public class T0821 {

	public static int SPLIT_COUNT = 300;
	public static void main(String[] args) {
		
		// input
		List<Integer> lst = getInputLst();
		
		// output
		List<List<Integer>> arrLst = getRetArrLst(lst);

		for (List<Integer> list : arrLst) {
			System.out.println(list);
		}
		
	}
	private static List<List<Integer>> getRetArrLst(List<Integer> lst) {
		
		List<List<Integer>> arrLst = new ArrayList<>();
		List<Integer> lstLoop = new ArrayList<>();
		
		for (int i = 0; i < lst.size(); i++) {
			lstLoop.add(lst.get(i));
			if (i!=0 && i%(SPLIT_COUNT-1) == 0) {
				arrLst.add(lstLoop);
				lstLoop = new ArrayList<>();
			}
		}
		if (!lstLoop.isEmpty()) {
			arrLst.add(lstLoop);
		}
		return arrLst;
	}
	private static List<Integer> getInputLst() {
		List<Integer> retLst = new ArrayList<>();
		for (int i = 1; i <= 800; i++) {
			retLst.add(Integer.valueOf(i));
		}
		return retLst;
	}

}
