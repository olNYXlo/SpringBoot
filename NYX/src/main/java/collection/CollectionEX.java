package collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionEX {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Collections jc = (Collections) context.getBean("Collections");
		
		jc.getAddressList();
		jc.getAddressMap();
		jc.getAddressSet();
		jc.getAddressProp();

	}
}
