package mySpringContext.service.interfaces;

/**
 * Proxy Configurator.
 * @author Ruslan Pipan
 **/
public interface ProxyConfigurator {
    Object replaceWithProxyIfNeeded(Object t, Class implClass);
}
