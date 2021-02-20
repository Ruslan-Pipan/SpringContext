package mySpringContext.service;

import java.util.Map;

/**
 * The class that is responsible for creating the context.
 * @author Ruslan Pipan
 * */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class,Class> ifc2ImplClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);
        context.setFactory(factory);
        return context;
    }
}
