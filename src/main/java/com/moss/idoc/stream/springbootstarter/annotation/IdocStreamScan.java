package com.moss.idoc.stream.springbootstarter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author david
 * @create 2022-07-01 08:46
 * @Description:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({IdocStreamScannerRegistrar.class})
public @interface IdocStreamScan {

	String[] basePackages() default {};
}
