import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.Application;
import play.GlobalSettings;

/**
 * 
 * @author mauricio
 *
 */

public class Global extends GlobalSettings {
	private ApplicationContext ctx;

	@Override
	public void onStart(Application app) {
		ctx = new ClassPathXmlApplicationContext("components.xml");
	}

	@Override
	public <A> A getControllerInstance(Class<A> clazz) {
		return ctx.getBean(clazz);
	}
}