package helloWorldAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


public class HelloWorldAnnotationApp {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldAnnotation.class);
		
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		helloWorld.setMessage("Ni Hao World!");
		helloWorld.getMessage();
		

	}
}
