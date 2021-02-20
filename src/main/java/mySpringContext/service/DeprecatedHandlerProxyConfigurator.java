package mySpringContext.service;

import mySpringContext.service.interfaces.ProxyConfigurator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Added extra logic to @Deprecated annotations.
 * @author Ruslan Pipan
 * */
public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

    /**
     * Replace original class to proxy class if needed.
     * */
    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)){

            if (implClass.getInterfaces().length == 0){
                return Enhancer.create(implClass, (InvocationHandler) (o, method, objects) -> getInvocationHandlerLogic(method, objects, t));
            }

            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (proxy, method, args) -> getInvocationHandlerLogic(method, args, t));
        }
        return t;
    }

    // Logic witch needs to add to original methods.
    private Object getInvocationHandlerLogic(Method method, Object[] args, Object t) throws IllegalAccessException, InvocationTargetException {
        System.out.println("************ ну ти і урод!!");
        return method.invoke(t, args);
    }
}
