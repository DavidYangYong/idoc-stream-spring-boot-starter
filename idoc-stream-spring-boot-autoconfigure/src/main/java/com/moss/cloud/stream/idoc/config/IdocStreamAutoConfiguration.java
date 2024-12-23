package com.moss.cloud.stream.idoc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moss.cloud.stream.idoc.domain.RuleProperties;
import com.moss.cloud.stream.idoc.listener.IdocListener;
import com.moss.cloud.stream.idoc.listener.IdocListenerCustomizer;
import com.moss.cloud.stream.idoc.listener.IdocListenerTemplate;
import com.moss.cloud.stream.idoc.service.base.DefaultIdocMessageConverter;
import com.moss.cloud.stream.idoc.service.base.IBaseExecService;
import com.moss.cloud.stream.idoc.service.base.IBaseTaskService;
import com.moss.cloud.stream.idoc.service.base.IdocMessageConverter;
import com.moss.cloud.stream.idoc.strategy.HandlerContext;
import com.moss.cloud.stream.idoc.strategy.IdocExecFactory;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
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
@ConditionalOnProperty(prefix = RuleProperties.STREAMIDOC_PREFIX, name = "enabled", havingValue = "true")
@Slf4j
public class IdocStreamAutoConfiguration {

  @Bean
  public HandlerContext handlerContext(ObjectProvider<List<IBaseExecService>> baseExecServiceList) {
    List<IBaseExecService> execServiceList = baseExecServiceList.getIfAvailable(
        Collections::emptyList);
    return new HandlerContext(execServiceList);
  }

  @Bean
  public IdocExecFactory idocExecFactory(HandlerContext handlerContext) {
    return new IdocExecFactory(handlerContext);
  }

  @Bean
  public IdocListenerTemplate idocListenerTemplate(IdocExecFactory idocExecFactory,
      RuleProperties ruleProperties,
      IdocMessageConverter idocMessageConverter, ObjectProvider<IBaseTaskService> baseTaskService) {
    IdocListenerTemplate idocListenerTemplate = new IdocListenerTemplate(idocExecFactory,
        ruleProperties);
    baseTaskService.ifAvailable(idocListenerTemplate::setBaseTaskService);
    idocListenerTemplate.setIdocMessageConverter(idocMessageConverter);
    return idocListenerTemplate;
  }

  @Bean
  @ConditionalOnMissingBean
  public IdocListener idocListener(IdocListenerTemplate idocListenerTemplate,
      ObjectProvider<List<IdocListenerCustomizer>> listObjectProvider) {
    IdocListener idocListener = new IdocListener();
    idocListener.setIdocListenerTemplate(idocListenerTemplate);
    listObjectProvider.ifAvailable(list -> {
      list.forEach(c -> {
        c.customize(idocListener);
      });
    });
    return idocListener;
  }

  @Bean
  @ConditionalOnMissingBean
  public IdocMessageConverter idocMessageConverter(ObjectMapper objectMapper) {
    return new DefaultIdocMessageConverter(objectMapper);
  }
}
