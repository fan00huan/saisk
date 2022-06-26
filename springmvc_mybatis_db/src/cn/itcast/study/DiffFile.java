package cn.itcast.study;


import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.itcast.ssm.common.core.MyConst;
import cn.itcast.ssm.common.file.MyDirectoryUtils;
import cn.itcast.ssm.common.file.MyFileConst;
import cn.itcast.ssm.common.file.MyFileUtils;
import cn.itcast.ssm.common.poi.MyPoiUtils;
import cn.itcast.ssm.common.str.MyDateUtil;
import cn.itcast.ssm.common.str.MyStrUtils;

public class DiffFile {

	//同じ、相違、新規、更新、削除
	public static final String RESULT_SAME = "同じ";
	public static final String RESULT_DIFF = "相違";
	public static final String RESULT_NEW = "新規";
	public static final String RESULT_DELETE = "削除";
	
	
	public static final String APP_VERSION = "2018.12.05 by sai";
	
	public static void main(String[] args) {

//		args = new String[4];
//		args[0]="C:\\printsystem\\workspace\\printsystem";
//		args[1]="C:\\printsystem\\workspace";
//		args[2]="C:\\souka\\pj\\print\\svn\\01source\\printsystem";
//		args[3]="C:\\souka\\pj\\print\\svn\\01source";

		try {
			run(args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void run(String[] args) throws Throwable {

		String time = MyDateUtil.getFormatDateTime(new Date(), MyConst.YYYYMMDDHH_MMSSMINUS_24);
		
		String folderNew = args[0];
		String headNew = args[1];
		String folderOld = args[2];
		String headOld = args[3];
		Map<String,File> mapNew = MyDirectoryUtils.getFilesTreeMapCutHead(folderNew, headNew, true);
		Map<String,File> mapOld = MyDirectoryUtils.getFilesTreeMapCutHead(folderOld, headOld, true);
		Set<String> allKey = new TreeSet<>();
		allKey.addAll(mapNew.keySet());
		allKey.addAll(mapOld.keySet());
		
		Workbook wb = MyPoiUtils.getWorkBook("template\\ソース差分一覧.xlsx");
		
		Sheet st = wb.getSheet("Sheet1");
		CellStyle cs = MyPoiUtils.getCellValueWithCs(st, "XXX");
		MyPoiUtils.setCellValueWithCs(st,  1, 3, headOld, cs);
		MyPoiUtils.setCellValueWithCs(st,  2, 3, headNew, cs);
		
		int index = 0;
		for (String filePath : allKey) {
			if (filePath.contains("\\.svn\\") || filePath.contains("\\classes\\") || filePath.contains("\\.apt_generated\\") || filePath.contains("\\.settings\\") || filePath.contains("\\build_tool\\")|| filePath.contains("\\test\\")|| filePath.contains("\\RemoteSystemsTempFiles\\")) {
				continue;
			}
			int rowNUm = index++ + 4;
			File fileNew = mapNew.get(filePath);
			File fileOld = mapOld.get(filePath);
			
			String timeNew = "";
			String timeOld = "";
			if (fileNew !=null) {
				timeNew = MyDateUtil.getDateTime(fileNew.lastModified(), MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24_SSS);	
			}
			if (fileOld !=null) {
				timeOld = MyDateUtil.getDateTime(fileOld.lastModified(), MyConst.YYYY_MM_DD_HH_MM_SS_SLASH_24_SSS);	
			}
			String result = "";
			
			if (MyStrUtils.isEmpty(timeNew) && MyStrUtils.isNotEmpty(timeOld)) {
				result = RESULT_DELETE;
			} else if (MyStrUtils.isNotEmpty(timeNew) && MyStrUtils.isEmpty(timeOld)) {
				result = RESULT_NEW;
			} else if (MyStrUtils.isNotEmpty(timeNew) && MyStrUtils.isNotEmpty(timeOld)) {
				if (timeNew.equals(timeOld)) {
					result = RESULT_SAME;
				} else {
					System.out.println("下記新旧ファイルを比較中...");
					System.out.println(fileNew.getAbsolutePath());
					System.out.println(fileOld.getAbsolutePath());
					result = MyFileUtils.getFileContent(fileNew, MyFileConst.ENCODE_UTF_8).toString().equals(MyFileUtils.getFileContent(fileOld, MyFileConst.ENCODE_UTF_8).toString()) ? RESULT_SAME :RESULT_DIFF;
					System.out.println("比較結果:["+result+"]");
				}
			} else {
				result = "tool error";
			}
			if (RESULT_NEW.equals(result) || RESULT_DIFF.equals(result)) {
				MyDirectoryUtils.copyFileByCmd(fileNew, "auto\\new_"+ time + "\\"+filePath.substring(0, filePath.lastIndexOf("\\")));
			}
			if (RESULT_DELETE.equals(result) || RESULT_DIFF.equals(result)) {
				MyDirectoryUtils.copyFileByCmd(fileOld, "auto\\old_"+ time + "\\"+filePath.substring(0, filePath.lastIndexOf("\\")));
			}
			
			MyPoiUtils.setCellValueWithCs(st,  rowNUm, 0, String.format("%06d", index), cs);
			MyPoiUtils.setCellValueWithCs(st,  rowNUm, 1, result, cs);
			MyPoiUtils.setCellValueWithCs(st,  rowNUm, 2, timeOld, cs);
			MyPoiUtils.setCellValueWithCs(st,  rowNUm, 3, timeNew, cs);
			MyPoiUtils.setCellValueWithCs(st,  rowNUm, 4, filePath, cs);
			
		}
		MyPoiUtils.writeXls(wb, "auto\\", "ソース差分一覧_at_" + time + ".xlsx"); 
		System.out.println(APP_VERSION);
	}

}
