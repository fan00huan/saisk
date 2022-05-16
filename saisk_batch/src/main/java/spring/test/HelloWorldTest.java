package spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloWorldTest {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
			"C:\\soft\\pleiades-2022-06-ultimate-win-64bit-jre_20220505\\workspace0515\\saisk_batch\\SpringTest.xml");

		SpringBean bean = (SpringBean) context.getBean("helloWorld");
 		bean.update();
		bean.show();
	}
}
