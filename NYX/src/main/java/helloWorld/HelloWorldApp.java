package helloWorld;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class HelloWorldApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		  //==================================================================
		  // example of bean scopes
	      HelloWorld objA = (HelloWorld) context.getBean("HelloWorld");
	      
	      objA.setMessage("I'm object A");
	      objA.getMessage();
	      //for singleton configuration, will only print I'm object A
	      //As the message attribute value of Ni Hao will be overwritten
	      // and when trying to create objB, will return objA
	      
	      // for prototype,  will print I'm object A + Ni Hao
	      // As objB will be instantiated with the message attribute
	      // value of Ni Hao
	      
	      HelloWorld objB = (HelloWorld) context.getBean("HelloWorld");
	      objB.getMessage();
	      //==================================================================
	      // example of init & destroy methods
	      // comment this portion of code to test bean scopes
	      AbstractApplicationContext context2 = new ClassPathXmlApplicationContext("Beans.xml");
	      HelloWorld objC = (HelloWorld) context2.getBean("HelloWorld");
	      
	      objC.setMessage("I'm object C");
	      objC.getMessage();
	      
	      HelloWorld objD = (HelloWorld) context2.getBean("HelloWorld");
	      objD.getMessage();
	      context2.registerShutdownHook();
	    //==================================================================
	      HelloWorld obj1 = (HelloWorld) context.getBean("HelloWorld");
	      obj1.getMessage();
	      obj1.getMessage2();
	      
	      NiHaoWorld obj2 = (NiHaoWorld) context.getBean("NiHaoWorld");
	      obj2.getMessage();
	      obj2.getMessage2();
	      obj2.getMessage3();
	      
	      
	}

}
