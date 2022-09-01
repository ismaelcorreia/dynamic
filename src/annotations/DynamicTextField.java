package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DynamicTextField {

    String label() default "label";
    String prompt() default "write something...";
    String iconSVG() default "..";
    String regex() default "";
    FieldLayout fieldLayout() default FieldLayout.BLOCK;
}
