package jp.co.yahoo.study011;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Study011903 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

//		String str = "Hello World";
//		System.out.println(str.toLowerCase());


		Calendar c = new GregorianCalendar();
		c.set(2019, 12 - 1, 31);

		Date date = c.getTime();

		// OK
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		String strDate1 = sdf1.format(date);
		System.out.println(strDate1);

		// NG
		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY/MM/dd");
		String strDate2 = sdf2.format(date);
		System.out.println(strDate2);

		// NG2
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyy/MM/dd");
		String strDate3 = sdf3.format(date);
		System.out.println(strDate3);

	}

}
