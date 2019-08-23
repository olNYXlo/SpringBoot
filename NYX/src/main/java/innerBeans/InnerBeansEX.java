package innerBeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InnerBeansEX {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      TextEditor te = (TextEditor) context.getBean("TextEditor");
	      te.spellCheck();
	  
	}
}
