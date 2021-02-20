package mySpringContext.service.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * Analog of annotations @Value
 *
 * @author Ruslan Pipan
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectProperty {
    String value() default "";
}
