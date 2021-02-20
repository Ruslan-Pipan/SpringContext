package mySpringContext.service.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * Analog of annotations @Autowired
 *
 * @author Ruslan Pipan
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectByType {
}
