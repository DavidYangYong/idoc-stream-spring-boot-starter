package com.moss.cloud.stream.idoc.config;

import com.moss.cloud.stream.idoc.listener.IdocListener;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
  public Function<Message<String>, Message<String>> idocSink() {
    return msg -> {
      String payload = msg.getPayload();
      log.info("idocSink Received payload: {}", payload);
      MessageHeaders messageHeaders = msg.getHeaders();
      log.info("idocSink Received messageHeaders: {}", messageHeaders);
      String processed = idocListener.process(messageHeaders, payload);
      return StringUtils.isNotEmpty(processed) ? MessageBuilder.createMessage(processed,
          messageHeaders) : null;
    };
  }
}
