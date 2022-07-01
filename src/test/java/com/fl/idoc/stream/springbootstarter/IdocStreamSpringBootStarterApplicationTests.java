package com.fl.idoc.stream.springbootstarter;

import com.fl.idoc.stream.springbootstarter.annotation.IdocStreamScan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {IdocStreamSpringBootStarterApplication.class})
@ActiveProfiles("test")
@IdocStreamScan(basePackages = "com.fl.idoc.stream.springbootstarter.service")
public class IdocStreamSpringBootStarterApplicationTests {
	@Test
	public void contextLoads() {
	}

}
