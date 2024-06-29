package com.moss.springboot.stream.idoc.autoconfigure;

import com.moss.springboot.stream.idoc.listener.IdocListener;
import com.moss.springboot.stream.idoc.service.base.IBaseTaskService;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author david
 * @create 2022-11-27 15:06
 * @Description: IdocSinkAutoConfiguration config
 **/
@Slf4j
@ConditionalOnClass({Message.class})
@AutoConfigureAfter({IdocStreamAutoConfiguration.class})
@ConditionalOnBean({IdocListener.class})
public class IdocSinkAutoConfiguration {

  private final IdocListener idocListener;

  public IdocSinkAutoConfiguration(IdocListener idocListener) {
    this.idocListener = idocListener;
  }


  @Bean
  public Supplier<String> idocSupplier(ObjectProvider<IBaseTaskService> baseTaskService) {
    return () -> {
      IBaseTaskService baseTask = baseTaskService.getIfAvailable();
      if (baseTask == null) {
        return null;
      }
      return baseTask.getMessage();
    };
  }

  @Bean
  public Function<Message<String>, Message<String>> idocSink() {
    return msg -> {
      String payload = msg.getPayload();
      log.info("idocSink Received payload: {}", payload);
      MessageHeaders messageHeaders = msg.getHeaders();
      log.info("idocSink Received messageHeaders: {}", messageHeaders);
      String payloadTemp;
      payloadTemp = idocListener.process(payload);
      if (StringUtils.isNotEmpty(payloadTemp)) {
        return MessageBuilder.createMessage(payloadTemp, messageHeaders);
      } else {
        return null;
      }
    };
  }
}
