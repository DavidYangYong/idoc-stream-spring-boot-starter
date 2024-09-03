package com.moss.springboot.stream.idoc.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moss.springboot.stream.idoc.domain.RuleProperties;
import com.moss.springboot.stream.idoc.factory.IdocExecFactory;
import com.moss.springboot.stream.idoc.listener.IdocListener;
import com.moss.springboot.stream.idoc.listener.IdocListenerCustomizer;
import com.moss.springboot.stream.idoc.listener.IdocListenerSupport;
import com.moss.springboot.stream.idoc.service.base.DefaultIdocMessageConverter;
import com.moss.springboot.stream.idoc.service.base.IBaseExecService;
import com.moss.springboot.stream.idoc.service.base.IBaseTaskService;
import com.moss.springboot.stream.idoc.service.base.IdocMessageConverter;
import com.moss.springboot.stream.idoc.strategy.HandlerContext;
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
  @ConditionalOnMissingBean
  public HandlerContext handlerContext(ObjectProvider<List<IBaseExecService>> baseExecServiceList) {
    List<IBaseExecService> execServiceList = baseExecServiceList.getIfAvailable(
        Collections::emptyList);
    return new HandlerContext(execServiceList);
  }

  @Bean
  @ConditionalOnMissingBean
  public IdocExecFactory idocExecFactory(HandlerContext handlerContext) {
    return new IdocExecFactory(handlerContext);
  }

  @Bean
  @ConditionalOnMissingBean
  public IdocListenerSupport idocListenerSupport(IdocExecFactory idocExecFactory,
      RuleProperties ruleProperties,
      IdocMessageConverter idocMessageConverter, ObjectProvider<IBaseTaskService> baseTaskService) {
    IdocListenerSupport idocListenerSupport = new IdocListenerSupport(idocExecFactory,
        ruleProperties);
    baseTaskService.ifAvailable(idocListenerSupport::setBaseTaskService);
    idocListenerSupport.setIdocMessageConverter(idocMessageConverter);
    return idocListenerSupport;
  }

  @Bean
  @ConditionalOnMissingBean
  public IdocListener idocListener(IdocListenerSupport idocListenerSupport,
      ObjectProvider<List<IdocListenerCustomizer>> listObjectProvider) {
    IdocListener idocListener = new IdocListener();
    idocListener.setIdocListenerSupport(idocListenerSupport);
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
