package injectingBeanDependencies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class injectingBeanDependenciesApp {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TextEditorAnnotation.class);
		
		TextEditor te = ctx.getBean(TextEditor.class);
		te.spellCheck();

	}
}
