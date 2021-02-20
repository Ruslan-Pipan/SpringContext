package mySpringContext.service;

import mySpringContext.service.interfaces.Config;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

/**
 * Responsible for giving the classes you want to create.
 * @author Ruslan Pipan
 * */
public class JavaConfig implements Config {
    // With witch is scanner packages.
    private final Reflections scanner;

    // The map contains specified classes.
    private final Map<Class,Class> ifc2ImplClass;

    public JavaConfig(String packageToScan, Map<Class,Class> ifc2ImplClass) {
        this.ifc2ImplClass = ifc2ImplClass;
        this.scanner = new Reflections(packageToScan);
    }

    /**
     * If the map contains a specified class than its returns.
     * Else scanner seeks implementation in the directory to which it was transferred.
     * */
    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
       return ifc2ImplClass.computeIfAbsent(ifc,aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if(classes.size() != 1){
                throw new RuntimeException(ifc + "has 0 or more than one impl please update your config.");
            }
            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }


}
