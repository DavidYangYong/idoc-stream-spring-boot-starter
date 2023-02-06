package com.fl.idoc.stream.springbootstarter;

import com.fl.idoc.stream.springbootstarter.autoconfigure.IdocStreamAutoConfiguration;
import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author david
 * @create 2019-04-19 10:18
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration
@ActiveProfiles("test")
@Slf4j
public class IdocStreamAutoConfigurationTest {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	;

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testFooCreatePropertyNull() {
		context.register(IdocStreamAutoConfiguration.class);
		context.refresh();
		IdocListener idocListener = context.getBean(IdocListener.class);
		Assert.assertNotNull(context.getBean(IdocListener.class));
	}

	@Test
	public void testFooCreatePropertyTrue() {
		Assert.assertNotNull(context.getBean(IdocListener.class));
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testFooCreatePropertyFalse() {
		log.debug("testFooCreatePropertyFalse");
		Assert.assertNull(context.getBean(IdocListener.class));
	}
}
