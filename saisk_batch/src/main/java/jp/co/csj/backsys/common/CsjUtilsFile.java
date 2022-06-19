/*****************************************************************************
 * プログラム ：CsjUtilsFile.java
 * 各種ファイルユーティリティ.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.csj.backsys.common.message.CsjMessageKey;
import jp.co.csj.backsys.common.property.CsjProperties;

/**
 * 各種ファイルユーティリティ.
 *
 * @author cui.shuangjia
 *
 */
public class CsjUtilsFile {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(CsjUtilsFile.class);

	/**
	 * 指定ファイルに書き込み操作.
	 *
	 * @param folder フォルダー
	 * @param fileNm ファイル名前
	 * @param enCode ファイルエンコード
	 * @param strList コンテント
	 * @throws Exception 例外
	 */
	public static void writeFile(String folder, String fileNm, String enCode, List<String> strList)
			throws Throwable {
		File file = new File(folder);
		file.mkdirs();
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(folder + fileNm), enCode));
		for (String str : strList) {
			writer.write(str);
			writer.write(CsjConsts.STR_ENTER);
		}
		writer.close();
	}

	/**
	 * 指定ファイルから読み込み操作.
	 *
	 * @param file ファイルオブジェクト
	 * @param encode ファイルエンコード
	 * @return 文字列リスト
	 */
	public static List<String> getFileContent(File file, String encode) throws Throwable {
		List<String> retList = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));

		while (reader.ready()) {
			retList.add(reader.readLine());
		}
		reader.close();
		return retList;
	}

	/**
	 * 指定ファイルから読み込み操作.
	 *
	 * @param filePath ファイルパス
	 * @param encode ファイルエンコード
	 * @return 文字列リスト
	 */
	public static List<String> getFileContent(String filePath, String encode) throws Throwable {
		return getFileContent(new File(filePath), encode);
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

	/**
	 * BufferedWriterコネクターを取得する.
	 *
	 * @param folder フォルダー
	 * @param fileName ファイル名称
	 * @param enCode エンコード
	 * @return
	 * @throws Throwable
	 */
	public static BufferedWriter getBufferedWriter(String folder, String fileName, String enCode, String batchId)
			throws Throwable {
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(folder + fileName), enCode));
		log.info(CsjProperties.getMsgLog(CsjMessageKey.I0008, CsjConsts.MSG_START, folder + fileName, batchId));
		return writer;
	}

	public static void closeBufferedWriter(BufferedWriter writer, String folder, String fileName, String batchId)
			throws Throwable {
		writer.close();
		log.info(CsjProperties.getMsgLog(CsjMessageKey.I0008, CsjConsts.MSG_END, folder + fileName, batchId));
	}

	public static void moveFile(String fromFile, String toFile, String batchId) throws Throwable {

		if (!new File(fromFile).isFile()) {
			return;
		}
   		new File(fromFile).renameTo(new File(toFile));

	}

	public static boolean isFile(String filePath) throws Throwable {
		return new File(filePath).isFile();
	}

	public static void makeStatusFile(String folder, String fileName, String enCode, String batchId) throws Throwable {
		BufferedWriter writer = getBufferedWriter(folder, fileName, enCode, batchId);
		writer.write(CsjConsts.STR_SUCESSFUL_STATUS);
		closeBufferedWriter(writer, folder, fileName, batchId);
	}

	public static void removeFile(File file, String serverNm, String batchId) {
		file.delete();
   		// {0}ファイル削除した。{1}（バッチID：{2}）
   		log.info(CsjProperties.getMsgLog(CsjMessageKey.I0022, serverNm, file.getAbsolutePath(), batchId));
	}
}
