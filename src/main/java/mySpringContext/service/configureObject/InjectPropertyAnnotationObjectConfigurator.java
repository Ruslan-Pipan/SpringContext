package mySpringContext.service.configureObject;

import lombok.SneakyThrows;
import mySpringContext.service.ApplicationContext;
import mySpringContext.service.annotations.InjectProperty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/***
 * Implements functional of annotation @InjectProperty
 *
 * @author Ruslan Pipan
 * */
public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    /***
     * This map contains all properties and their key from file "application.properties".
     * */
    private final Map<String, String> propertiesMap;

    /**
     * During the creation and initialization of the map.
     * */
    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator(){
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("application.properties")).getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    /**
     * If variables have annotations @InjectProperty their value should be in the file application.properties.
     * Keys can be both name value or value in @InjectProperty.
     * */
    @SneakyThrows
    @Override
    public void configure(Object o, ApplicationContext context) {
        Class<?> implClass = o.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if (annotation != null){
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(o,value);
            }
        }
    }
}
