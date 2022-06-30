package com.fl.idoc.stream.springbootstarter.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.idoc.stream.springbootstarter.factory.IdocExecFactory;
import com.fl.idoc.stream.springbootstarter.listener.RuleProperties;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/12/20 0020.
 *
 * @author david
 */
@Configuration
@ConditionalOnClass({Sink.class, ObjectMapper.class})
@EnableConfigurationProperties({RuleProperties.class})
@ConditionalOnProperty(prefix = "fl.cloud.idoc.stream", name = "enabled", havingValue = "true")
@Slf4j
public class IdocStreamAutoConfiguration {

	@Autowired
	private IdocExecFactory idocExecFactory;


	public void init() {
		log.debug("IdocStreamAutoConfiguration start");
	}

	@Autowired
	private HandlerProcessor handlerProcessor;

	public IdocStreamAutoConfiguration() {

		init();
		idocExecFactory.setHandlerContext(handlerProcessor.createHandlerContext());
	}
}
