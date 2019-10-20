package com.seyrancom.consulting.core.web;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Controller
@Inherited
public @interface AppController {
    String value() default "";
}