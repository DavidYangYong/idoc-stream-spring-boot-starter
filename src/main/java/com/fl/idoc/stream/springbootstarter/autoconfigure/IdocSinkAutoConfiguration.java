package com.fl.idoc.stream.springbootstarter.autoconfigure;

import com.fl.idoc.stream.springbootstarter.listener.IdocListener;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author david
 * @create 2022-11-27 15:06
 * @Description:
 **/
@Slf4j
@AutoConfigureAfter({IdocStreamAutoConfiguration.class})
public class IdocSinkAutoConfiguration {

	@Autowired
	private IdocListener idocListener;

	public IdocSinkAutoConfiguration() {

		init();
	}

	public void init() {
		log.debug("IdocSinkAutoConfiguration start");
	}

	@Bean
	public Function<Message<String>, Message<String>> idocSink() {
		return new Function<Message<String>, Message<String>>() {
			@Override
			public Message<String> apply(Message<String> msg) {
				String payload = msg.getPayload();
				log.info("idocSink Received: {}", payload);
				String payloadTemp = null;
				payloadTemp = idocListener.process(payload);
				if (StringUtils.isNotEmpty(payloadTemp)) {
					return MessageBuilder.withPayload(payloadTemp)
							.build();
				} else {
					return null;
				}
			}
		};
	}
}
