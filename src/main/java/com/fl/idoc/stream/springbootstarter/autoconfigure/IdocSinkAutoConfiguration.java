package com.fl.idoc.stream.springbootstarter.autoconfigure;

import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author david
 * @create 2022-11-27 15:06
 * @Description:
 **/
@Configuration
@Slf4j
@AutoConfigureAfter({IdocStreamAutoConfiguration.class})
public class IdocSinkAutoConfiguration {

	@Autowired
	private IdocListener idocListener;

	@Bean
	public Function<String, String> sink() {
		return input -> {
			log.info("sink Received: {}", input);
			String resultString = idocListener.process(input);
			return resultString;
		};
	}
}
