package com.seyrancom.consulting.core.web;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@Inherited
public @interface AppRestController {
    String value() default "";
}