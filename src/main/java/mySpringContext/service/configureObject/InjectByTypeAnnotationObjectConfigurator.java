package mySpringContext.service.configureObject;

import lombok.SneakyThrows;
import mySpringContext.service.ApplicationContext;
import mySpringContext.service.annotations.InjectByType;

import java.lang.reflect.Field;

/***
 * Implements functional of annotation @InjectByType
 *
 * @author Ruslan Pipan
 * */
public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    /**
     * If variables have annotations @InjectByType, it is joined by ApplicationContext.
     **/
    @SneakyThrows
    @Override
    public void configure(Object o, ApplicationContext context) {
        for (Field field : o.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(o,object);
            }
        }
    }
}
