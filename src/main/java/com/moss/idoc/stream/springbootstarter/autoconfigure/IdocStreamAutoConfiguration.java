package com.moss.idoc.stream.springbootstarter.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moss.idoc.stream.springbootstarter.factory.IdocExecFactory;
import com.moss.idoc.stream.springbootstarter.listener.IdocListener;
import com.moss.idoc.stream.springbootstarter.listener.IdocListenerSupport;
import com.moss.idoc.stream.springbootstarter.listener.RuleProperties;
import com.moss.idoc.stream.springbootstarter.service.base.DefaultIdocMessageConverter;
import com.moss.idoc.stream.springbootstarter.service.base.IBaseTaskService;
import com.moss.idoc.stream.springbootstarter.service.base.IdocMessageConverter;
import com.moss.idoc.stream.springbootstarter.strategy.HandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
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

	private ObjectMapper objectMapper;

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

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
	public IdocListenerSupport idocListenerSupport(IdocExecFactory idocExecFactory, RuleProperties ruleProperties,
			IdocMessageConverter idocMessageConverter, ObjectProvider<IBaseTaskService> baseTaskServices) {
		IdocListenerSupport idocListenerSupport = new IdocListenerSupport(idocExecFactory, ruleProperties);
		IBaseTaskService baseTaskService = baseTaskServices.getIfAvailable();
		if (baseTaskService != null) {
			idocListenerSupport.setBaseTaskService(baseTaskService);
		}
		idocListenerSupport.setIdocMessageConverter(idocMessageConverter);
		return idocListenerSupport;
	}

	@Bean
	@ConditionalOnMissingBean
	public IdocListener idocListener(IdocListenerSupport idocListenerSupport) {
		IdocListener idocListener = new IdocListener();
		idocListener.setIdocListenerSupport(idocListenerSupport);
		idocListener.setObjectMapper(objectMapper);
		return idocListener;
	}

	@Bean
	@ConditionalOnMissingBean
	public IdocMessageConverter idocMessageConverter() {
		return new DefaultIdocMessageConverter(objectMapper);
	}

}
