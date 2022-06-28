package com.fl.idoc.stream.springbootstarter.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import com.fl.idoc.stream.springbootstarter.listener.IdocListenerSupport;
import com.fl.idoc.stream.springbootstarter.listener.RuleProperties;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
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

	@Resource
	private RuleProperties ruleProperties;


	public IdocStreamAutoConfiguration() {
		init();
	}


	public void init() {
		log.debug("IdocStreamAutoConfiguration start");
	}

	@Bean
	@ConditionalOnMissingBean(IdocListenerSupport.class)
	IdocListenerSupport idocListenerSupport() {
		return new IdocListenerSupport(ruleProperties);
	}
	@Bean
	@ConditionalOnMissingBean(IdocListener.class)
	IdocListener idocListener() {
		return new IdocListener();
	}


}
