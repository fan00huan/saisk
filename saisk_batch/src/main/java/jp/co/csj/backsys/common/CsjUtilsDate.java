/*****************************************************************************
 * プログラム ：CsjUtilsDate.java
 * 各種日付ユーティリティクラス.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jp.co.csj.backsys.common.exception.CsjExceptionHandle;

/**
 * 各種日付ユーティリティ.
 *
 * @author cui.shuangjia
 *
 */
public final class CsjUtilsDate {

    /**
     * 日付文字列から指定フォマードで日付文字列に転換する.
     *
     * @param dateStr 日付文字列
     * @param format フォマード形式
     * @return
     */
    public static Date getDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    /**
     * 指定日付を取得する.
     *
     * @param year 年
     * @param month 月
     * @param date 日
     * @param hourOfDay 時
     * @param minute 分
     * @param second 秒
     * @return
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute,
            int second) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }

    /**
     * カレント日付は指定フォマードで日付文字列に転換する.
     *
     * @param format フォマード形式
     * @return
     */
    public static String getCurrentDate(String format) throws ParseException {
        return getFormatDate(new Date(), format);
    }

    /**
     * 日付は指定フォマードで日付文字列に転換する.
     *
     * @param date 日付
     * @param format フォマード形式
     * @return
     */
    public static String getFormatDate(Date date, String format) {

        String ret = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ret = sdf.format(date);
        } catch (Exception e) {
            // 処理なし
        	new CsjExceptionHandle(e).outputLog();
        }
        return ret;
    }


    /**
     * 指定フォマーで日付文字列に転換する.
     *
     * @param second 日付Long
     * @param format フォマード形式
     * @return
     */
    public static String getStrDate(long second, String format) throws ParseException {
        return getFormatDate(new Date(second), format);
    }

    /**
     * 性能性能テストため、経過時間を計算する.
     *
     * @param ms 時間差
     * @return
     */
    public static String longTimeToDay(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "-");
        }
        Long hour = (ms - day * dd) / hh;
        if (hour > 0) {
            sb.append(String.format("%02d", Integer.valueOf(hour.toString()).intValue()) + ":");
        } else {
            sb.append("00:");
        }
        Long minute = (ms - day * dd - hour * hh) / mi;
        if (minute > 0) {
            sb.append(String.format("%02d", Integer.valueOf(minute.toString()).intValue()) + ":");
        } else {
            sb.append("00:");
        }
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        if (second > 0) {
            sb.append(String.format("%02d", Integer.valueOf(second.toString()).intValue()) + ":");
        } else {
            sb.append("00:");
        }
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        if (milliSecond > 0) {
            sb.append(String.format("%03d", Integer.valueOf(milliSecond.toString()).intValue()));
        } else {
            sb.append("000");
        }
        return sb.toString();
    }


	/**
	 * 現在日付から1か月前の年月日情報の取得
	 * @return
	 */
	public static String getRealMinusMonthYmd(String pattern) {

		// 出力フォーマットの指定(年月のみ)
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern(pattern);
		// 現在日時を前月にして年月の情報のみ抽出
		String fdate = dtformat1.format(LocalDateTime.now().minusMonths(1));

		return fdate;

	}

	public static String getRealPreMonthDate(String formatter) {

		// 出力フォーマットの指定(年月のみ)
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern(formatter);
		// 現在日時を前月にして年月の情報のみ抽出
		String fdate = dtformat1.format(LocalDateTime.now().minusMonths(1));

		return fdate;

	}
	public static Date getFormatDateAdd(Date date, int amount) throws Exception {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();
	}

	public static Date getFormatMonthAdd(Date date, int amount) throws Exception {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MONTH, amount);
		return cal.getTime();
	}
	public static String getPreMonthFirstDay(Date date, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		Calendar cale =  Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, -1);
		cale.set(Calendar.DAY_OF_MONTH, 1);

		return format.format(cale.getTime());
	}

	public static String getPreMonthLastDay(Date date, String patten) {

		SimpleDateFormat format = new SimpleDateFormat(patten);

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return format.format(cale.getTime());
	}

	public static Date getPreDate(String date, String patten) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(date, patten));
        calendar.add(Calendar.DATE,-1);
		return calendar.getTime();
	}

	/**
	 *
	 * @param yyyyMM
	 * @return
	 * @throws Throwable
	 */
	public static Date getMonthLastDate(String yyyyMM) throws Throwable {
		Calendar cale = Calendar.getInstance();
		cale.setTime(getDate(yyyyMM + "01", CsjConsts.YYYY_MM_DD));
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cale.getTime();
	}
}
