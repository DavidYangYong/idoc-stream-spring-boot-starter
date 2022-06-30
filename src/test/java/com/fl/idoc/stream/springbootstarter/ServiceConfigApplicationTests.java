package com.fl.idoc.stream.springbootstarter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.fl.idoc.stream.springbootstarter.autoconfigure.IdocStreamAutoConfiguration;
import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import com.fl.idoc.stream.springbootstarter.listener.IdocListenerSupport;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {IdocStreamAutoConfiguration.class})
@ActiveProfiles("test")
public class ServiceConfigApplicationTests {

	@MockBean
	private IdocStreamAutoConfiguration idocStreamAutoConfiguration;
	@MockBean
	private IdocListener idocListener;
	@MockBean
	private IdocListenerSupport idocListenerSupport;

	@MockBean
	private HandlerProcessor handlerProcessor;


	@Test
	public void contextLoads() {
	}

	@Test
	public void idocStreamAutoConfiguration() {
		assertNotNull(idocStreamAutoConfiguration);
	}

	@Test
	public void idocListener() {
		assertNotNull(idocListener);
	}

	@Test
	public void idocListenerSupport() {
		assertNotNull(idocListenerSupport);
	}

	@Test
	public void handlerProcessor() {
		assertNotNull(handlerProcessor);
	}
}
