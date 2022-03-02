package com.cfg.sevenapp.ms_intern.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SevenLogger {

    String message() default "Audit Message";

}
