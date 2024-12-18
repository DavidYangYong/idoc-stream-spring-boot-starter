package com.moss.springboot.stream.idoc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.moss.cloud.stream.idoc.config.IdocFunctionAutoConfiguration;
import com.moss.cloud.stream.idoc.config.IdocStreamAutoConfiguration;
import com.moss.cloud.stream.idoc.listener.IdocListener;
import com.moss.cloud.stream.idoc.listener.IdocListenerSupport;
import com.moss.cloud.stream.idoc.strategy.IdocExecFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {IdocStreamAutoConfiguration.class, IdocFunctionAutoConfiguration.class,
		JacksonAutoConfiguration.class})
@ActiveProfiles("test")
public class ServiceConfigApplicationTests {

	@MockBean
	private IdocStreamAutoConfiguration idocStreamAutoConfiguration;
	@MockBean
	private IdocFunctionAutoConfiguration idocSinkAutoConfiguration;

	@MockBean
	private IdocExecFactory idocExecFactory;

	@MockBean
	private IdocListenerSupport idocListenerSupport;

	@MockBean
	private IdocListener idocListener;


	@Test
	public void contextLoads() {
	}

	@Test
	public void idocStreamAutoConfiguration() {
		assertNotNull(idocStreamAutoConfiguration);
	}


	@Test
	public void idocSinkAutoConfiguration() {
		assertNotNull(idocSinkAutoConfiguration);
	}


	@Test
	public void idocExecFactory() {
		assertNotNull(idocExecFactory);
	}

	@Test
	public void idocListenerSupport() {
		assertNotNull(idocListenerSupport);
	}

	@Test
	public void idocListener() {
		assertNotNull(idocListener);
	}
}
