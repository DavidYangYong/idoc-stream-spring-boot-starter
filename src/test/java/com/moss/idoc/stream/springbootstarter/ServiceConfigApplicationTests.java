package com.moss.idoc.stream.springbootstarter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.moss.idoc.stream.springbootstarter.autoconfigure.IdocSinkAutoConfiguration;
import com.moss.idoc.stream.springbootstarter.autoconfigure.IdocStreamAutoConfiguration;
import com.moss.idoc.stream.springbootstarter.factory.IdocExecFactory;
import com.moss.idoc.stream.springbootstarter.listener.IdocListener;
import com.moss.idoc.stream.springbootstarter.listener.IdocListenerSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {IdocStreamAutoConfiguration.class, IdocSinkAutoConfiguration.class, JacksonAutoConfiguration.class})
@ActiveProfiles("test")
public class ServiceConfigApplicationTests {

	@MockBean
	private IdocStreamAutoConfiguration idocStreamAutoConfiguration;
	@MockBean
	private IdocSinkAutoConfiguration idocSinkAutoConfiguration;

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
