package com.moss.cloud.stream.idoc.config;

import com.moss.cloud.stream.idoc.listener.IdocListener;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author david
 * @create 2022-11-27 15:06
 * @Description: IdocFunctionAutoConfiguration config
 **/
@Slf4j
@ConditionalOnClass({Message.class})
@AutoConfigureAfter({IdocStreamAutoConfiguration.class})
@ConditionalOnBean({IdocListener.class})
public class IdocFunctionAutoConfiguration {

  private final IdocListener idocListener;

  public IdocFunctionAutoConfiguration(IdocListener idocListener) {
    this.idocListener = idocListener;
  }

  @Bean
  @ConditionalOnMissingBean
  public Function<Message<String>, Message<String>> idocSink() {
    return msg -> {
      String payload = msg.getPayload();
      MessageHeaders messageHeaders = msg.getHeaders();
      log.info("idocSink Received payload {} and messageHeaders: {}", payload, messageHeaders);

      String processed = idocListener.process(messageHeaders, payload);
      if (StringUtils.isNotEmpty(processed)) {
        Message<String> message = MessageBuilder.createMessage(processed,
            messageHeaders);
        String type = idocListener.queryProcessMsgType(messageHeaders);
        message = MessageBuilder.withPayload(processed).copyHeaders(messageHeaders).setHeader("routingKeyExpression",
            type).build();
        return message;
      }
      return null;
    };
  }

  @Bean
  @ConditionalOnMissingBean
  public Consumer<Message<String>> idocConsumer() {
    return msg -> {
      String payload = msg.getPayload();
      MessageHeaders messageHeaders = msg.getHeaders();
      log.info("idocConsumer Received payload {} and messageHeaders: {}", payload, messageHeaders);
      idocListener.process(messageHeaders, payload);
    };
  }
}
