package com.moss.cloud.stream.idoc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * @author david
 * @date 2019-10-31 09:52
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {

	@AliasFor("name")
	String value() default "";

	@AliasFor("value")
	String name() default "";

	boolean idocContentNotConvert() default true;
}
