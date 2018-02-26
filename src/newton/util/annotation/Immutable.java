package newton.util.annotation;

import java.lang.annotation.*;

/**
 * This annotation is used to tag classes as immutable.
 *
 * @author Melf Kammholz
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Immutable {

}
