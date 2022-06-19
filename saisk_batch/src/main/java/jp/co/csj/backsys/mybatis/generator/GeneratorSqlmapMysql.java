/*****************************************************************************
 * プログラム ：GeneratorSqlmapMysql.java
 * 開発者用　MYBATIS　クラス＆マッパー自動生成.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.mybatis.generator;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorSqlmapMysql {

    public void generator(String filePath) throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(filePath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }

    public static void main(String[] args) throws Exception {
        try {

            String s_f_s = System.getProperty("file.separator");

//jp.co.csj.backsys.mapper.original
            deleteGenerator("jp" + s_f_s + "co" + s_f_s + "csj" + s_f_s + "backsys" + s_f_s + "mapper" + s_f_s + "original");
            deleteGenerator("jp" + s_f_s + "co" + s_f_s + "csj" + s_f_s + "backsys" + s_f_s + "po" + s_f_s + "original");

            GeneratorSqlmapMysql generatorSqlmap = new GeneratorSqlmapMysql();
            generatorSqlmap.generator("res\\MyBatisGenerator\\generatorConfigMysql.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteGenerator(String pkg) {

        String s_pj_path = System.getProperty("user.dir") + System.getProperty("file.separator");
        String folderPath = s_pj_path + "src" + System.getProperty("file.separator")  + "main" + System.getProperty("file.separator")  + "java" + System.getProperty("file.separator") +pkg;

        try {

            System.out.println(folderPath);
            System.out.println("-------------------");
            delFile(folderPath, false, true);
        } catch (Throwable e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        System.out.println(11);
    }



    /**
     * @param String filePath
     * @param boolean isHaveSubFile
     * @return
     */
    public static LinkedList<File> getFilesList(String filePath, boolean isHaveSubFile) {

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

    public static void delFile(String path, boolean haveSub, boolean deleteMyself)
            throws Throwable {
        try {
            File f = new File(path);
            if (f.isDirectory()) {
                List<File> fLst = getFilesList(f.getAbsolutePath(), haveSub);
                for (File ft : fLst) {
                    delFile(ft);
                }
                if (deleteMyself) {
                    delFile(f);
                }
            } else if (f.isFile()) {
                delFile(f);
            }
        } catch (Throwable e) {
            throw e;
        }

    }


    public static void delFile(File f) {

        System.out.println(f.getAbsolutePath());
        f.delete();

    }


}
