package helloWorldAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HelloWorldAnnotation {
	
	@Bean
	public HelloWorld helloWorld() {
		return new HelloWorld();
	}

}


