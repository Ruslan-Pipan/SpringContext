package mySpringContext.service.configureObject;

import mySpringContext.service.ApplicationContext;

/**
 * Classes related to object configuration should implement this interface.
 *
 * @author Ruslan Pipan
 */
public interface ObjectConfigurator {
    void configure(Object o, ApplicationContext context);
}
