package mySpringContext.service.interfaces;

import org.reflections.Reflections;

/**
 * Configuration-related classes must implement this interface.
 * @author Ruslan Pipan
 * */
public interface Config {
    /**
     * Method witch get impel class.
     * */
    <T> Class<? extends T> getImplClass(Class<T> ifc);
    /**
     * Method witch get impel scanner.
     * */
    Reflections getScanner();
}
