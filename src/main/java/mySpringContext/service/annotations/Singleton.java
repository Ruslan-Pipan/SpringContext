package mySpringContext.service.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * Designation classes will be singleton.
 *
 * @author Ruslan Pipan
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {
}

