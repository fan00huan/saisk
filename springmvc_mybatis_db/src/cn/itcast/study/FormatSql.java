package cn.itcast.study;


import java.util.ArrayList;
import java.util.List;

import cn.itcast.ssm.common.core.MyLog5j;
import cn.itcast.ssm.common.file.MyFileConst;
import cn.itcast.ssm.common.file.MyFileUtils;
import cn.itcast.ssm.common.str.MyStrUtils;

public class FormatSql {

	public static void main(String[] args) {

		try {
			run();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		
	}

	private static void run() throws Throwable {
		List<String> strLst = MyFileUtils.getFileContent("from.sql", MyFileConst.ENCODE_UTF_8);
		
		String sql = "";
		List<String> valLst = new ArrayList<>();
		List<String> typeLst = new ArrayList<>();
		for (String str : strLst) {
			if (str.contains("Preparing:")) {
				sql = MyStrUtils.fromAtoBByTrim(str, "Preparing:", "");
			}  else if (str.contains("Parameters:")) {
				str = MyStrUtils.fromAtoBByTrim(str, "Parameters:", "");
				for (String s : str.split(",",-1)) {
					String val = MyStrUtils.fromAtoBByTrim(s, "", "(");
					String type = MyStrUtils.fromAtoBByTrim(s, "(", ")");
					valLst.add(val);
					typeLst.add(type);
				}
				break;
			}
		}
		
		
		int index = 0;
		
		StringBuffer sb = new StringBuffer();
		for (char ch : sql.toCharArray()) {
			if (ch == '?') {
				if (typeLst.get(index).toLowerCase().contains("int")) {
					sb.append(valLst.get(index));
				} else {
					sb.append("'"+valLst.get(index)+"'");
				}
				index++;
			} else {
				sb.append(ch);
			}
		}
		System.out.println(sb.toString());
		
		MyLog5j.initLog5j("", "to.sql", MyFileConst.ENCODE_UTF_8);
		MyLog5j.write(sb.toString());
		MyLog5j.closeLog5j();
	}

}
