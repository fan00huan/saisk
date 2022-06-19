package spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloWorldTest {
	public static void main(String[] args) throws Exception {
        
		ApplicationContext context = new FileSystemXmlApplicationContext(
			"classpath:spring/SpringTest.xml");

		SpringBean bean = (SpringBean) context.getBean("helloWorld");
 		bean.update();
		bean.show();
	}
}
