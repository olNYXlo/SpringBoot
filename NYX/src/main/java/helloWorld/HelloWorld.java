package helloWorld;

public class HelloWorld {

	private String message;
	private String message2;

	public void getMessage() {
		System.out.println("Your message : " + message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public void getMessage2() {
		System.out.println("Your message2 : " + message2);
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}

	public void init() {
		System.out.println("Initializing Bean");
	}
	
	public void destroy() {
		System.out.println("Destroying Bean");
	}
	
}
