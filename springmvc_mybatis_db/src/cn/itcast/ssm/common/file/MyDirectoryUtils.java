/**
 * Copyright (c) 2012, Java CNC/CSJ for tools. All rights reserved.
 */
package cn.itcast.ssm.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import cn.itcast.ssm.common.core.MyProcess;
import cn.itcast.ssm.common.str.MyRegConstStr;
import cn.itcast.ssm.common.str.MyStrUtils;
/**
 * CsjDirectory
 * @author cuishuangjia@163.com
 * @version 1.0
 * @since 1.6
 */
public class MyDirectoryUtils {


	public static Logger log = Logger.getLogger(MyDirectoryUtils.class);
	
	public static LinkedList<File> getFilesListWithOutStr(String filePath,
			boolean isHaveSubFile, String startStr, String endStr) {

		LinkedList<File> tList = getFilesList(filePath, isHaveSubFile);
		LinkedList<File> retList = new LinkedList<File>();
		for (File f : tList) {
			String fNm = f.getName().toLowerCase();
			if (MyStrUtils.isNotEmpty(startStr) && fNm.startsWith(startStr)) {
				continue;
			} else if (MyStrUtils.isNotEmpty(endStr) && fNm.endsWith(endStr)) {
				continue;
			}
		}
		return retList;
	}

	public static boolean isFiles(String text, String splitStr) {

		boolean retVal = true;
		String[] strArr = text.split(splitStr);
		for (String str : strArr) {
			if (MyStrUtils.isEmpty(str)) {
				continue;
			}
			File f = new File(str);
			if (f.isFile() == false) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}
	/**
	 * * @param
	 */
	public static int checkFilePath(String filePath) {
		int retVal = MyFileConst.IS_ERROR;
		File f = new File(filePath);
		if (f.isDirectory()) {
			retVal = MyFileConst.IS_PATH;
		} else if (f.isFile()) {
			retVal = MyFileConst.IS_FILE;
		} else if (filePath.contains(";") && isFiles(filePath, ";")) {
			retVal = MyFileConst.IS_FILES;
		}
		return retVal;
	}
	public static LinkedList<File> getExcelFileByAbsPath(String filePath,boolean isHaveSubFile) {
		LinkedList<File> retList = new LinkedList<File>();
		int retVal = checkFilePath(filePath);
		if (retVal == MyFileConst.IS_PATH) {
			retList= getFilesListReg(filePath, isHaveSubFile,MyRegConstStr.EXCEL_REG_DOT);
		} else if (retVal == MyFileConst.IS_FILE) {
			retList.add(new File(filePath));
		} else if (retVal == MyFileConst.IS_FILES) {
			String[] strArr = filePath.split(",");
			for (String str : strArr) {
				if (MyStrUtils.isNotEmpty(str)) {
					retList.add(new File(str));
				}
			}
		}
		return retList;
	}
	public static LinkedList<File> getFilesListWithStr(String filePath,
			boolean isHaveSubFile,String startStr,String endStr) {

		LinkedList<File> tList =getFilesList(filePath,isHaveSubFile);
		LinkedList<File> retList = new LinkedList<File>();
		for (File f : tList) {
			String fNm = f.getName().toLowerCase();
			if (MyStrUtils.isNotEmpty(startStr)&& fNm.startsWith(startStr)) {
					retList.add(f);
			} else if (MyStrUtils.isNotEmpty(endStr)&& fNm.endsWith(endStr)) {
				retList.add(f);
			}
		}
		return retList;
	}
	public static TreeMap<Long,File> getFilesModifyTimeMapReg(String filePath,
			boolean isHaveSubFile,String regex) {

		LinkedList<File> tList =getFilesList(filePath,isHaveSubFile);
		TreeMap<Long,File> retMap = new TreeMap<Long, File>();
		for (File f : tList) {
			String fNm = f.getName().toLowerCase();
			
			if (MyStrUtils.isEmpty(regex)||fNm.matches(regex)) {
				retMap.put(f.lastModified(), f);
			}
		}
		return retMap;
	}
	public static LinkedList<File> getFilesListReg(String filePath,
			boolean isHaveSubFile,String regex) {

		LinkedList<File> fList = getFilesList(filePath,isHaveSubFile);
		LinkedList<File> retList = new LinkedList<File>();

		for (File f : fList) {
			if (MyStrUtils.isEmpty(regex)||f.getName().matches(regex)) {
				retList.add(f);
			}
		}
		return retList;
	}
	/**
	 * @param String filePath
	 * @param boolean isHaveSubFile
	 * @return
	 */
	public static LinkedList<File> getFilesList(String filePath,
			boolean isHaveSubFile) {

		LinkedList<File> retList = new LinkedList<File>();
		LinkedList<File> list = new LinkedList<File>();
		File dir = new File(filePath);
		File file[] = dir.listFiles();
		if (null == file) {
		return retList;
		}

		for (int i = 0; i < file.length; i++) {

			if (file[i].isDirectory())
				list.add(file[i]);
			else
				retList.add(file[i]);
		}

		if (isHaveSubFile) {
			File tmp;
			while (!list.isEmpty()) {
				tmp = list.removeFirst();

				if (tmp.isDirectory()) {
					file = tmp.listFiles();
					if (file == null)
						continue;
					for (int i = 0; i < file.length; i++) {
						if (file[i].isDirectory())
							list.add(file[i]);
						else {
							retList.add(file[i]);
						}
					}
				} else {
					retList.add(tmp);
				}
			}
		}

		return retList;
	}

	public static boolean reNameFile(File f, String newNm) {
		File mm = new File(f.getParent() + MyProcess.s_f_s + newNm);
		return f.renameTo(mm);
	}
	public static LinkedHashMap<String, File> getFilesMap(String filePath, boolean isHaveSubFile) {
		LinkedHashMap<String, File> retMap = new LinkedHashMap<String, File>();
		List<File> fileList = getFilesList(filePath, isHaveSubFile);
		for (File f : fileList) {
			retMap.put(f.getName(), f);
		}
		return retMap;
	}
	public static Map<String, File> getFilesTreeMap(String filePath, boolean isHaveSubFile) {
		Map<String, File> retMap = new TreeMap<String, File>();
		List<File> fileList = getFilesList(filePath, isHaveSubFile);
		for (File f : fileList) {
			retMap.put(f.getAbsolutePath(), f);
		}
		return retMap;
	}
	public static Map<String, File> getFilesTreeMapCutHead(String filePath, String head, boolean isHaveSubFile) {
		Map<String, File> retMap = new TreeMap<String, File>();
		List<File> fileList = getFilesList(filePath, isHaveSubFile);
		for (File f : fileList) {
			String key = MyStrUtils.fromAtoBByTrim(f.getAbsolutePath(), head, "");
			if (key.contains("PRN00901_T2M_HOKAN.cdd")) {
				System.out.println(1);
			}
			retMap.put(key, f);
			log.info(f.getAbsolutePath());
		}
		return retMap;
	}
	public static long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		FileInputStream fis = null;
		try {
			if (f.exists()) {
				
				fis = new FileInputStream(f);
				s = fis.available();
			} else {
				f.createNewFile();
				System.out.println("file not exist!");
			}
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
			}
		}
		return s;
	}

	public static String formatFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
	public static String getFileSize(File f) throws Exception {
		return formatFileSize(getFileSizes(f));

	}
	public static long getFileMaxLine(File f) throws Exception {

		long retVal = 0;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), MyFileConst.ENCODE_SHIFT_JIS));

			while (reader.ready()) {
				reader.readLine();
				retVal++;
			}
			reader.close();
		return retVal;
	}
	public static void removeFiles(String filePath) throws Throwable {
		try {
			File f = new File(filePath);
			if (f!=null) {
				delFile(f);
			}
		} catch (Throwable e) {
			throw e;
		}

	}

	public static void delFile(String path,String fileNmReg,boolean haveSub) throws Throwable {
		try {
			File f = new File(path);
			if (MyStrUtils.isEmpty(fileNmReg)) {
				delFile(f);
			}else if (f.isDirectory()) {
				List<File> fLst = getFilesList(f.getAbsolutePath(), haveSub);
				for (File ft : fLst) {
					if (MyStrUtils.isEmpty(fileNmReg)) {
						ft.delete();
					}else if (ft.getName().matches(fileNmReg)) {
						ft.delete();
					}
				}
			}
		} catch (Throwable e) {
			throw e;
		}

	}
	public static void delFile(File f)throws Throwable {
		try {
			if (f == null) {
				return;
			}
			if (f.isDirectory()) {
				File[] list = f.listFiles();
				for (int i = 0; i < list.length; i++) {
					if (list[i].isDirectory()) {
						delFile(list[i]);
					}else{
						if(list[i].isFile())
							list[i].delete();
					}
				}
				f.delete();
			} else {
				if (f.isFile())
					f.delete();
			}
		} catch (Throwable e) {
			throw e;
		}
	}


	private static String formatToFolder(String folder) {
		if (folder.endsWith(MyProcess.s_f_s)) {
			return folder;
		} else {
			return folder + MyProcess.s_f_s;
		}
	}
	/**
	 * @param fromFolder
	 * @param fromFile
	 * @param toFolder
	 * @param toFile
	 * @throws Throwable 
	 */
	public static void copyFile(String fromFolder, String fromFile,
			String toFolder, String toFile,boolean toFolderAutoCreate) throws Throwable {
		
		try {
			if (toFolderAutoCreate) {
				File makeFolder = new File(toFolder);
				makeFolder.mkdirs();
			}
			boolean isHaveSubFile = false;
			if (fromFolder.toLowerCase().startsWith("havesub:")) {
				isHaveSubFile = true;
			}
			fromFolder = MyStrUtils.fromAtoBByTrim(fromFolder,"havesub:", "");
			
			boolean isReg = false;
			if (fromFile.startsWith("reg:")) {
				isReg = true;
			}
			fromFile = MyStrUtils.fromAtoBByTrim(fromFile,"reg:", "");
			
			String fromStr = formatToFolder(fromFolder)+fromFile;
			if (isReg) {
				fromStr = formatToFolder(fromFolder);
			}
			String toStr = formatToFolder(toFolder)+toFile;
			File from = new File(fromStr);
			File to = new File(toStr);
			
			if (from.isDirectory()) {
				if (to.isDirectory()) {
					if (isReg) {
						LinkedList<File> fileLst = getFilesListReg(fromFolder, isHaveSubFile,fromFile);
						for (File f : fileLst) {
							String toPath = toStr+MyStrUtils.fromAtoBByTrim(f.getParent()+MyProcess.s_f_s, fromStr, "");
							new File(toPath).mkdirs();
							String batStr = "xcopy " + f.getAbsolutePath() + " " + toPath + " /y /k /c /R";
							Process process  = Runtime.getRuntime().exec(batStr);
							process.waitFor();
						}
					} else {
						LinkedList<File> fileLst = getFilesList(fromFolder, isHaveSubFile);
						for (File f : fileLst) {
							String toPath = toStr+MyStrUtils.fromAtoBByTrim(f.getParent()+MyProcess.s_f_s, fromStr, "");
							new File(toPath).mkdirs();
							String batStr = "xcopy " + f.getAbsolutePath() + " " + toPath + " /y /k /c /R";
							Process process  = Runtime.getRuntime().exec(batStr);
							process.waitFor();
						}
					}
				} else {
					throw new Exception("xxxxxxxxxxxxxxx");
				}
			} else if (from.isFile()) {
				if (to.isDirectory()) {
					String batStr = "xcopy " + from.getAbsolutePath() + " " + to + " /y  /k /c /R";
					Process process  = Runtime.getRuntime().exec(batStr);
					process.waitFor();
				} else {
					File tmpToFolder = new File(toFolder);
					if (tmpToFolder.isDirectory()) {
						String batStr = "xcopy " + from.getAbsolutePath() + " " + toFolder + " /y  /k /c /R";
						Process process  = Runtime.getRuntime().exec(batStr);
						process.waitFor();
						
						File f = new File(toFolder+toFile);
						if (f.isFile()) {
							f.delete();
						}
						File fr = new File(toFolder+fromFile);
						if (fr.isFile()) {
							fr.renameTo(f);
						}
					} else {
						throw new Exception("xxxxxxxxxxxxxxxxxxxxxxx");
					}
				}
			} else {
				throw new Exception("xxxxxxxxxxxxxxxxxxxxx");
			}
		} catch (Throwable e) {
			throw e;
		}
	}

	public static void copyFileByCmd(File f, String toPath) {
		new File(toPath).mkdirs();
		String batStr = "xcopy " + f.getAbsolutePath() + " " + toPath + " /y /k /c /R";
		try {
			Process process = Runtime.getRuntime().exec(batStr);
			process.waitFor();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
