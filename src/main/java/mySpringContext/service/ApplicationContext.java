package mySpringContext.service;

import mySpringContext.service.annotations.Singleton;
import mySpringContext.service.interfaces.Config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Responsible for the delivery of the object.
 *
 * @author Ruslan Pipan
 * */
public class ApplicationContext {
    /**
     * Can create new object.
     * */
    private ObjectFactory factory;


    /**
     * It's map contains created objects.
     * */
    private final Map<Class,Object> cache = new ConcurrentHashMap<>();


    /**
     * Can return object witch contains in config.
     * */
    private final Config config;

    /**
     * When creating ApplicationContext, should pass config.
     * */
    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type){
        // If object present in cache we don't need to recreate it.
        if (cache.containsKey(type))
            return (T) cache.get(type);

        Class<? extends T> implClass = type;

        // If type is interface then we take its implementations from the config.
        if (type.isInterface()){
            implClass = config.getImplClass(type);
        }

        // Creating new object.
        T t = factory.createObject(implClass);

        // If the object is Singleton we put it in the map.
        if (implClass.isAnnotationPresent(Singleton.class)){
            cache.put(type, t);
        }

        return t;
    }

    /**
     * ApplicationContext can return config.
     * */
    public Config getConfig() {
        return config;
    }

    /**
     * Also needed for work ObjectFactory.
     * */
    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }
}
