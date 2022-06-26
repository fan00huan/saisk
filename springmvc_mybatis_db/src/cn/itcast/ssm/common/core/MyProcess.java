/**
 *
 */
package cn.itcast.ssm.common.core;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Properties;

/**
 * @author Think
 *
 */
public class MyProcess {
	public static String s_pj_path = "";
	public static int s_pj_path_length = 0;
	public static String s_f_s = "";
	public static String s_user = "";
	public static String s_local_host = "";
	public static String s_local_userdomain = "";
	public static String s_local_usercountry = "";
	
	public static String s_local_os = "";
	public static String s_local_os_arch = "";
	
	
	public static String s_out_ip = "";
	public static String s_local_inet_addr_with_line = "";
	public static String s_local_inet_addr = "";
	public static Date s_default_date;
	
	public static final String s_log4j_file_path = "";
	public static String s_pc_info = "";
	public static String s_db_log_pc_info = "";
	public static String   s_newLine   =   "";

	static {

		try {
			Properties   pp   =   System.getProperties();
			s_newLine = pp.getProperty( "line.separator");
			s_f_s = System.getProperty("file.separator");

			// 取得当前路径
			s_pj_path = System.getProperty("user.dir") + s_f_s;
			s_pj_path_length = s_pj_path.length();
			
			s_local_usercountry = System.getProperty("user.country");

			s_local_os = System.getProperty("os.name");
			s_local_os_arch = System.getProperty("os.arch");
			s_local_host =InetAddress.getLocalHost().toString();
			s_user = System.getenv().get("USERNAME");// 获取用户名
			

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void openFile(String filePath)  {
		try {
			Runtime.getRuntime().exec("cmd /c start "+ filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delFile(String filePath) {

		Process process = null;
		File f = new File(filePath);
		try {

			if (f.isDirectory()) {
				process = Runtime.getRuntime().exec("cmd /c  rd /q /s " + f.getAbsolutePath());
				process.waitFor();
			} else if (f.isFile()) {
				process = Runtime.getRuntime().exec("cmd /c del " + f.getAbsolutePath());
				process.waitFor();
			} else {
				System.out.println("error");
				return;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public static void copyFile(String fromFilePath, String toFilePath) {
		try {
			Process process = null;
			File fromFile = new File(fromFilePath);
			File toFile = new File(toFilePath);
			toFile.mkdirs();
			String batStr = "xcopy " + fromFile.getAbsolutePath() + "\\*.* " + toFile.getAbsolutePath()
					+ "\\ /y /e /k /c /R";
			process = Runtime.getRuntime().exec(batStr);
			process.waitFor();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
