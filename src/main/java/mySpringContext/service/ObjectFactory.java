package mySpringContext.service;


import lombok.SneakyThrows;
import mySpringContext.service.configureObject.ObjectConfigurator;
import mySpringContext.service.interfaces.ProxyConfigurator;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for creating objects.
 * @author Ruslan Pipan
 * */
public class ObjectFactory {
    // List all object witch needed to configure.
    private final List<ObjectConfigurator> configurators = new ArrayList<>();

    // List all object witch needed to replace proxy.
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    private final ApplicationContext context;


    @SneakyThrows
    public ObjectFactory(ApplicationContext context){
    this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }


    @SneakyThrows
    public <T> T createObject(Class<T> implClass){

        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        t = repleaceWithProxyIfNeeded(implClass, t);

        return t;
    }

    /**
     * Release Proxy.
     * */
    private <T> T repleaceWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator configurator : proxyConfigurators) {
            t = (T) configurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    /**
     * Configure object.
     * */
    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t,context));
    }

    /**
     * Created object.
     * */
    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }

    /**
     * Invoke init method.
     **/
    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)){
                method.invoke(t);
            }
        }
    }

}
