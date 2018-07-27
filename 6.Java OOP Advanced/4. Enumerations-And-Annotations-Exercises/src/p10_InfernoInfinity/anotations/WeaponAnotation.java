package p10_InfernoInfinity.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeaponAnotation {

    String author();
    int revision();
    String description();
    String[] reviewers();

}
