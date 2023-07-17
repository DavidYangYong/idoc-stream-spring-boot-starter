package com.fl.idoc.stream.springbootstarter.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.idoc.stream.springbootstarter.factory.IdocExecFactory;
import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import com.fl.idoc.stream.springbootstarter.listener.IdocListenerSupport;
import com.fl.idoc.stream.springbootstarter.listener.RuleProperties;
import com.fl.idoc.stream.springbootstarter.service.base.IBaseTaskService;
import com.fl.idoc.stream.springbootstarter.strategy.HandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2018/12/20 0020.
 *
 * @author david
 */
@AutoConfiguration
@ConditionalOnClass({ObjectMapper.class})
@EnableConfigurationProperties({RuleProperties.class})
@ConditionalOnProperty(prefix = "com.fl.cloud.idoc.stream", name = "enabled", havingValue = "true")
@Slf4j
public class IdocStreamAutoConfiguration {

	public void init() {
		log.debug("IdocStreamAutoConfiguration start");
	}

	public IdocStreamAutoConfiguration() {

		init();
	}

	@Autowired(required = false)
	public void setBaseTaskService(IBaseTaskService baseTaskService) {
		this.baseTaskService = baseTaskService;
	}
	private IBaseTaskService baseTaskService;

	@Bean
	@ConditionalOnMissingBean
	public HandlerContext handlerContext() {
		return new HandlerContext();
	}

	@Bean
	@ConditionalOnMissingBean
	public IdocExecFactory idocExecFactory(HandlerContext handlerContext) {
		return new IdocExecFactory(handlerContext);
	}

	@Bean
	@ConditionalOnMissingBean
	public IdocListenerSupport idocListenerSupport(IdocExecFactory idocExecFactory, RuleProperties ruleProperties) {
		IdocListenerSupport idocListenerSupport = new IdocListenerSupport(idocExecFactory, ruleProperties);
		idocListenerSupport.setBaseTaskService(baseTaskService);
		return idocListenerSupport;
	}

	@Bean
	@ConditionalOnMissingBean
	public IdocListener idocListener(IdocListenerSupport idocListenerSupport, ObjectMapper objectMapper) {
		IdocListener idocListener = new IdocListener();
		idocListener.setIdocListenerSupport(idocListenerSupport);
		idocListener.setObjectMapper(objectMapper);
		return idocListener;
	}
}
