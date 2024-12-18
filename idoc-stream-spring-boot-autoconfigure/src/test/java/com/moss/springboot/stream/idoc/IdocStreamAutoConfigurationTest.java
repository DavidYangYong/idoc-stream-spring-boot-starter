package com.moss.springboot.stream.idoc;

import static org.assertj.core.api.Assertions.assertThat;

import com.moss.cloud.stream.idoc.config.IdocStreamAutoConfiguration;
import com.moss.cloud.stream.idoc.listener.IdocListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * @author david
 * @create 2019-04-19 10:18
 **/
@Slf4j
public class IdocStreamAutoConfigurationTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(IdocStreamAutoConfiguration.class, JacksonAutoConfiguration.class));

	@Test
	public void testIdocStreamAutoConfiguration() {
		this.contextRunner
				.withPropertyValues("com.fl.cloud.idoc.stream.enabled=true")
				.withUserConfiguration(IdocStreamAutoConfiguration.class).run((context) -> {
					assertThat(context).hasSingleBean(IdocListener.class);
				});
	}
}
