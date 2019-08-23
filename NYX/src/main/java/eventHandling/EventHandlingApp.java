package eventHandling;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventHandlingApp {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		context.start();
		
		HelloWorld obj = (HelloWorld) context.getBean("HelloWorld");
		obj.getMessage();
		
		context.stop();
	}
}
