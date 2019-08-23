package customEventHandler;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomEventHandlerApp {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		CustomEventPublisher cvp = (CustomEventPublisher) context.getBean("customEventPublisher");
		
		cvp.publish();
		cvp.publish();

	}
}
