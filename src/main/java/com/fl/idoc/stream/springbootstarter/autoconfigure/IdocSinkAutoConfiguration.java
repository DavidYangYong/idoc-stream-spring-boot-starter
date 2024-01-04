package com.fl.idoc.stream.springbootstarter.autoconfigure;

import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
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
@AutoConfigureAfter({IdocStreamAutoConfiguration.class})
public class IdocSinkAutoConfiguration {

	private final IdocListener idocListener;


	public IdocSinkAutoConfiguration(IdocListener idocListener) {

		init();
		this.idocListener = idocListener;
	}

	public void init() {
		log.debug("IdocSinkAutoConfiguration start");
	}

	@Bean
	public Function<Message<String>, Message<String>> idocSink() {
		return msg -> {
			String payload = msg.getPayload();
			log.info("idocSink Received payload: {}", payload);
			MessageHeaders messageHeaders = msg.getHeaders();
			log.info("idocSink Received messageHeaders: {}", messageHeaders);
			String payloadTemp = null;
			payloadTemp = idocListener.process(payload);
			if (StringUtils.isNotEmpty(payloadTemp)) {
				return MessageBuilder.createMessage(payloadTemp, messageHeaders);
			} else {
				return null;
			}
		};
	}
}
