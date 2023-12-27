package com.fl.idoc.stream.springbootstarter;

import static org.assertj.core.api.Assertions.assertThat;
import com.fl.idoc.stream.springbootstarter.autoconfigure.IdocStreamAutoConfiguration;
import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author david
 * @create 2019-04-19 10:18
 **/
@SpringBootTest
@ContextConfiguration(classes = {IdocStreamAutoConfiguration.class})
@ActiveProfiles("test")
@Slf4j
public class IdocStreamAutoConfigurationTest {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(IdocStreamAutoConfiguration.class, JacksonAutoConfiguration.class));

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

	@Autowired
	private IdocListener idocListener;

	@Test
	public void testIdocListener() {
		log.debug("testIdocListener");
		Assert.assertNotNull(idocListener);
	}

	@Test
	public void testIdocStreamAutoConfiguration() {
		this.contextRunner
				.withPropertyValues("com.fl.cloud.idoc.stream.enabled=true")
				.withUserConfiguration(IdocStreamAutoConfiguration.class).run((context) -> {
					assertThat(context).hasSingleBean(IdocListener.class);
				});
	}
}
